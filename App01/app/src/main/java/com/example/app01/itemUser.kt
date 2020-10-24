package com.example.app01

data class itemUser (
    var id : Int,
    var role : Int, // 1 if boss, 2 if worker
    var nick : String,
    var account : String
)