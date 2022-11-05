package com.nevidimka655.lab5.entities

data class StudentGroup(
    val name: String = "",
    val facultyName: String = "",
    val educationLevel: Int = 0,
    val flagContractExists: Boolean = false,
    val flagPrivilegeExists: Boolean = false
)