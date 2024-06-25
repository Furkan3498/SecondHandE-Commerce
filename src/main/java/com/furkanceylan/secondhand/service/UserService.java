package com.furkanceylan.secondhand.service;

import com.furkanceylan.secondhand.dto.CreateUserRequest;
import com.furkanceylan.secondhand.dto.UpdateUserRequest;
import com.furkanceylan.secondhand.dto.UserDto;
import com.furkanceylan.secondhand.dto.UserDtoConverter;
import com.furkanceylan.secondhand.exceptions.UserNotFoundException;
import com.furkanceylan.secondhand.model.User;
import com.furkanceylan.secondhand.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDtoConverter userDtoConverter;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository,UserDtoConverter userDtoConverter) {
        this.userDtoConverter = userDtoConverter;
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userDtoConverter::convert)
                .collect(Collectors.toList());
    }

    /*
     * throw -> hatayı fırlatır.
     * throws -> hata fırlatmaz ama geliştiriciyi uyarır. Bu metodu çağıran diğer yerlere şunu declare
     * edersin getUserById çağırıyorsun ama buradan böyle bir hata gelebilir ona göre kendi try catch metodunu
     * yaz. Method signature'ına bunu yazarak uyarıyorsun.
     * */

    public UserDto getUserByMail(String mail){
        User user = findUserByMail(mail);
        return userDtoConverter.convert(user);
    }
    /*
     * Kotlinde ki immutable muhabbetinden dolayı setle null oluşan objeyi initalize edemiyorum.
     * Constructor geçmek zorundayım. Bu seferde tüm elemanları constructor'da istiyor. Id'yi mecburen
     * null vermek zorunda kaldık. Normalde Entity nesnelerde bu tarz durumlarda hibernate'den faydalanıyorduk.
     * Ama kotlinde id için böyle bir durum söz konusu değil. Id özelinde de hata çıkmamasının sebebi
     * Id'nin @field:GeneratedValue(strategy = GenerationType.IDENTITY) anatasyonu ile mysql tarafında
     * oluşturulması.
     * */
    public UserDto createUser(CreateUserRequest createUserRequest) {
        User userInformation = new User(createUserRequest.getMail(),createUserRequest.getFirstName()
                ,createUserRequest.getLastName(),createUserRequest.getMiddleName(),false);

        //false verelim isActive parametresini active veya deactiveUser metodlarım zaten var.

        return userDtoConverter.convert(userRepository.save(userInformation));
    }

    public UserDto updateUser(String mail, UpdateUserRequest updateUserRequest) {
        User userInformation = findUserByMail(mail);

        //Model nesnesi
        User updatedUserInformation = new User(userInformation.getId(),userInformation.getMail(),updateUserRequest.getFirstName(), updateUserRequest.getLastName(),
                updateUserRequest.getMiddleName(),userInformation.getActive());

        return userDtoConverter.convert(userRepository.save(updatedUserInformation));
    }

    public void deactivateUser(Long id) {
        changeActivateUser(id,false);
    }

    public void activateUser(Long id) {
        changeActivateUser(id,true);
    }

    public void deleteUser(Long id) {

            userRepository.deleteById(id);

    }


    private User findUserByMail(String mail){
        return userRepository.findByMail(mail).orElseThrow(()->new UserNotFoundException("User couldn't be found by following mail: "+mail));
    }

    private User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User couldn't be found by following id: "+id));
    }

    private void changeActivateUser(Long id,Boolean isActive){
        User userInformation = findUserById(id);

        User updatedUserInformation = new User(userInformation.getId(),
                userInformation.getMail(),
                userInformation.getFirstName(),
                userInformation.getLastName(),
                userInformation.getMiddleName(),
                isActive);

        userRepository.save(updatedUserInformation);
    }
}