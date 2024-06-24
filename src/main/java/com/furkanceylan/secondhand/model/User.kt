package com.furkanceylan.secondhand.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
data class User(

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     val  id: Long ;
    val mail: String = "",
    val firstName: String = "",

    val lastName: String = "",
    val middleName: String = ""

) {

}





