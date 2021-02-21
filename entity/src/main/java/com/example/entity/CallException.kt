package com.example.entity

data class CallException<ErrorBody>(val errorCode: Int, val errorMessage:String?=null, val errorBody: ErrorBody? = null,)