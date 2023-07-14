package com.ariqbelajar.interntest.utils

fun checkPalindrome(text: String): Boolean {
    // Remove non-alphanumeric characters and convert to lowercase
    val normalizedText = text.replace(Regex("[^A-Za-z0-9]"), "").toLowerCase()

    // Check if the normalized text is equal to its reversed form
    return normalizedText == normalizedText.reversed()
}