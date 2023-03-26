package com.example.my

import android.util.Log


class ListLangMapper {
    public val listA=listOf("English", "普通话", "हिन्दी", "Español", "Português", "Русский", "日本語", "Deutsch", "한국어", "Français", "Türkçe", "Italiano", "Tiếng Việt", "Polski", "Українська", "ქართული", "Bahasa Indonesia", "ภาษาไทย", "Nederlands", "Română", "Čeština", "Ελληνικά")
    public val listB=listOf("en", "zh", "hi", "es", "pt", "ru", "ja", "de", "ko", "fr", "tr", "it", "vi", "pl", "uk", "ka", "id", "th", "nl", "ro", "cs", "el")



    init {
        require(listA.size == listB.size) { "List size should match" }
    }

    fun maptolong(value: String): String {

        val index = listB.indexOf(value)
        Log.d("TAG", "Я получил список $value")
        require(index >= 0) { "Value not found in input listррав" }
        return(listA[index])
}

    fun maptoshort(value: String): String {

        val index = listA.indexOf(value)
        Log.d("TAG", "Я получил список $value")
        require(index >= 0) { "Value not found in input listрраsssв" }
        return(listB[index])
    }

        fun Pos(value: String): Int {

        val index = listA.indexOf(value)
        Log.d("TAG", "Я получил список $value")
        require(index >= 0) { "Value not found in input listвввввв" }
        return index
    }


}