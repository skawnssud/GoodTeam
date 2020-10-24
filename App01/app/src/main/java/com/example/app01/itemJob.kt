package com.example.app01

data class itemJob (
    var id : Int,
    var id_worker : Int,
    var id_branch : Int,
    var title : String,
    var type_pay : Int, // 1 if monthly payment, 2 if weekly, 3 if range
    var date_pay : Int, // payment date | 1 ~ 31 if type_pay == 1, 1 ~ 7 (mon ~ sun) if 2, 1 ~ 31 if 3
    var date_start : String,
    var pay : Int,
    var time_start : Int,
    var time_end : Int,
    var time_rest_start : Int,
    var time_rest_end : Int,
    var insurance : Boolean,
    var extra : Boolean,
    var extend : Boolean,
    var night : Boolean
)

