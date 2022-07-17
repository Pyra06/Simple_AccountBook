package com.example.accountbookv1

class User {
    var id : Int = 0
    var name : String = ""
    var type : String = ""

    constructor(name: String, type: String){
//        this.nameId = nameId nameId: Int,     var nameId : Int = 0
        this.name = name
        this.type = type
    }

    constructor(){
    }

    override fun toString(): String {
        return "$name"
    }
}