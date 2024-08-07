package com.furkanceylan.secondhand.service;

import com.furkanceylan.secondhand.dto.CreateUserRequest;
import com.furkanceylan.secondhand.dto.UpdateUserRequest;
import com.furkanceylan.secondhand.dto.UserDto;
import com.furkanceylan.secondhand.dto.UserDtoConverter;
import com.furkanceylan.secondhand.exceptions.UserIsNotActiveException;
import com.furkanceylan.secondhand.exceptions.UserNotFoundException;
import com.furkanceylan.secondhand.model.Users;
import com.furkanceylan.secondhand.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    private static final Logger logger= LoggerFactory.getLogger(UserService.class);
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
        Users user = findUserByMail(mail);
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
    public UserDto createUser(final CreateUserRequest createUserRequest) {
        Users userInformation = new Users(createUserRequest.getMail(),createUserRequest.getFirstName()
                ,createUserRequest.getLastName(),createUserRequest.getMiddleName(),false);

        //false verelim isActive parametresini active veya deactiveUser metodlarım zaten var.

        return userDtoConverter.convert(userRepository.save(userInformation));
    }

    public UserDto updateUser(final String mail,final UpdateUserRequest updateUserRequest) {
        Users userInformation = findUserByMail(mail);
        logger.warn(String.format("The user wanted update is not Acitve , user mail: %s" , mail));

        if (!userInformation.getActive()){
            throw  new UserIsNotActiveException();
        }

        //Model nesnesi
        Users updatedUserInformation = new Users(userInformation.getId(),userInformation.getMail(),updateUserRequest.getFirstName(), updateUserRequest.getLastName(),
                updateUserRequest.getMiddleName(),userInformation.getActive());

        return userDtoConverter.convert(userRepository.save(updatedUserInformation));
    }

    public void deactivateUser(final Long id) {
        changeActivateUser(id,false);
    }

    public void activateUser(final Long id) {
        changeActivateUser(id,true);
    }

    public void deleteUser(final Long id) {

            userRepository.deleteById(id);

    }


    private Users findUserByMail( final String mail){
        return userRepository.findByMail(mail).orElseThrow(()->new UserNotFoundException("User couldn't be found by following mail: "+mail));
    }

    protected Users findUserById( final Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User couldn't be found by following id: "+id));
    }

    private void changeActivateUser( final Long id,Boolean isActive){
        Users userInformation = findUserById(id);

        Users updatedUserInformation = new Users(userInformation.getId(),
                userInformation.getMail(),
                userInformation.getFirstName(),
                userInformation.getLastName(),
                userInformation.getMiddleName(),
                isActive);

        userRepository.save(updatedUserInformation);
    }

}