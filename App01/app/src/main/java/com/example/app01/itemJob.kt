package com.example.app01

data class itemJob (var title : String) {
    var id : Int = 0
    var id_worker : Int = 0
    var id_branch : Int = 0
    var type_pay : Int = 1 // 1 if monthly payment, 2 if weekly, 3 if range
    var date_pay : Int = 1 // payment date | 1 ~ 31 if type_pay == 1, 1 ~ 7 (mon ~ sun) if 2, 1 ~ 31 if 3
    var date_start : String = ""
    var pay : Int = 0
    var time_start : Int = 900
    var time_end : Int = 1800
    var time_rest_start : Int = 1200
    var time_rest_end : Int = 1300
    var insurance : Boolean = true
    var extra : Boolean = true
    var extend : Boolean = true
    var night : Boolean = true
}

