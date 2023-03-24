package com.example.my

data class Country(val image: Int, val name: String)

object Countries {

    private val images = intArrayOf(
        R.drawable.gb,
        R.drawable.china,
        R.drawable.indi,
        R.drawable.span,
        R.drawable.portu,
        R.drawable.rus,
        R.drawable.jpn,
        R.drawable.germ,
        R.drawable.korea,
        R.drawable.fr,
        R.drawable.tur,
        R.drawable.it,
        R.drawable.viet,
        R.drawable.pol,
        R.drawable.ukr,
        R.drawable.span,
        R.drawable.indo,
        R.drawable.tha,
        R.drawable.holand,
        R.drawable.rumin,
        R.drawable.chz,
        R.drawable.grees
    )

    private val countries = arrayOf(
        "English",
        "普通话",
        "हिन्दी",
        "Español",
        "Português",
        "Русский",
        "日本語",
        "Deutsch",
        "한국어",
        "Français",
        "Türkçe",
        "Italiano",
        "Tiếng Việt",
        "Polski",
        "Українська",
        "ქართული",
        "Bahasa Indonesia",
        "ภาษาไทย",
        "Nederlands",
        "Română",
        "Čeština",
        "Ελληνικά"
    )

    var list: ArrayList<Country>? = null
        get() {

            if (field != null)
                return field

            field = ArrayList()
            for (i in images.indices) {

                val imageId = images[i]
                val countryName = countries[i]

                val country = Country(imageId, countryName)
                field!!.add(country)
            }

            return field
        }
}