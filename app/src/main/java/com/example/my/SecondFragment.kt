package com.example.my

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.my.databinding.FragmentSecondBinding
import java.util.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
lateinit var resources: Resources

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val lang = Locale.getDefault().language
        val mySharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val LangSaved: String = mySharedPreferences.getString("LANGUI1", "Nohave").toString()
        Log.d("TAG", "Язык сохраненный в настройках $LangSaved")

        if (LangSaved == "Nohave") {

            Log.d("TAG", "Язык не настроен")

            val mapper = ListLangMapper()

            val langmaped = mapper.maptolong(lang)
            mySharedPreferences.edit().putString("LANGUI1", langmaped).apply()

            val LangSaved1: String = mySharedPreferences.getString("LANGUI1", "Nohave").toString()
            Log.d("TAG", "Я настроил язык как в Телефоне $LangSaved1")
        }





        _binding = FragmentSecondBinding.inflate(inflater, container, false)



        return binding.root




    }



    private fun setupSimpleSpinner() {

        val adapter = CountryArrayAdapter(requireContext(), Countries.list!!)
        adapter.setDropDownViewResource(R.layout.spinner_back)
        val simpleSpinner = view!!.findViewById<Spinner>(R.id.spinner)
        simpleSpinner?.adapter = adapter


        val mySharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val Save_lang: String = mySharedPreferences.getString("LANGUI1", "English").toString()
        Log.d("TAG", "Я получидл язык пир создании спинера $Save_lang")

        val mapper = ListLangMapper()
        val spinnerPosition = mapper.Pos(Save_lang)
        Log.d("TAG", "Я получидл язык пир создании спинера $Save_lang")

        Log.d("TAG", "Я получидл позицию пир создании спинера $spinnerPosition")

        simpleSpinner?.setSelection(spinnerPosition)

        simpleSpinner?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_UP) {
                    val imm: InputMethodManager =
                        activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

                    var view = activity!!.currentFocus

                    if (view == null) {
                        view = View(activity)
                    }
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
                return false
            }
        })


        simpleSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val selectedItem = parent!!.getItemAtPosition(position).toString()
                val selectedItemname = selectedItem.substringAfterLast("=").substringBefore(')')

                val mySharedPreferences: SharedPreferences =
                    requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
                Log.d("TAG", "Пользователь изменил язык на $selectedItemname")
                val mapper = ListLangMapper()

                val shortlangname = mapper.maptoshort(selectedItemname)

                mySharedPreferences.edit().putString("LANGUI1", "$selectedItemname").apply()

                val config = resources.configuration
                val lang = config.getLocales().toString().substringBefore(']').substringAfter('[')


                if (lang!=shortlangname) {
                    Log.d("TAG", "Сравнились $lang и $shortlangname")
                    //     oldConfig = resources.configuration

                    config.setLocale(Locale(shortlangname)) // устанавливаем язык на английский
                    resources.updateConfiguration(
                        config,
                        resources.displayMetrics
                    ) // обновляем конфигурацию приложения.
                    val lang1 = config.getLocales().toString()
                    Log.d("TAG", "Применен язык на $lang1")
                    findNavController().navigate(R.id.action_SecondFragment_to_SecondFragment)
                }




            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Use as per your wish
            }

        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            setupSimpleSpinner()




        val mySharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val Save_name: String = mySharedPreferences.getString("NAME", "").toString()
        val Save_sname: String = mySharedPreferences.getString("SNAME", "").toString()
        val Save_bdate: String = mySharedPreferences.getString("BDATE", null).toString()
        val Save_btime: String = mySharedPreferences.getString("BTIME", null).toString()
        val Save_bcity: String = mySharedPreferences.getString("BCITY", "").toString()
        val Save_lang: String = mySharedPreferences.getString("LANGUI1", "English").toString()


        Log.d("TAG", "Я получидл язык $Save_lang")

        val simpleSpinner = view!!.findViewById<Spinner>(R.id.spinner)





        val editName = view.findViewById<EditText>(R.id.FirstName)
        editName?.setText("$Save_name")
        val editSname = view.findViewById<EditText>(R.id.LastName)
        editSname?.setText("$Save_sname")
        val editBdate = view.findViewById<EditText>(R.id.BirthDate)
        editBdate?.setText("$Save_bdate")
        val editBcity = view.findViewById<EditText>(R.id.BirthCity)
        editBcity?.setText("$Save_bcity")
        val editBtime = view.findViewById<EditText>(R.id.BirthTime)
        editBtime?.setText("$Save_btime")




        binding.ButtonSave.setOnClickListener {

            val mySharedPreferences: SharedPreferences =
                requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            val editName = view.findViewById<EditText>(R.id.FirstName)
            val editNameString = editName.text.toString()

            val editSame = view.findViewById<EditText>(R.id.LastName)
            val editSameString = editSame.text.toString()

            val editBdate = view.findViewById<EditText>(R.id.BirthDate)
            val editBdateString = editBdate.text.toString()

            val editBcity = view.findViewById<EditText>(R.id.BirthCity)
            val editbCityString = editBcity.text.toString()

            val editBtime = view.findViewById<EditText>(R.id.BirthTime)

            val editBtimeString = editBtime.text?.toString()
            Log.d("TAG", "Я получидл $editBtimeString")

            //         if (editName_String.isNotBlank() && editSame_String.isNotBlank() && editВdate_String.isNotBlank() && editВtime_String.isNotBlank()&& editВcity_String.isNotBlank()) {

                mySharedPreferences.edit().putString("NAME", "$editNameString").apply()
                mySharedPreferences.edit().putString("SNAME", "$editSameString").apply()
                mySharedPreferences.edit().putString("BDATE", "$editBdateString").apply()
                mySharedPreferences.edit().putString("BTIME", "$editBtimeString").apply()
                mySharedPreferences.edit().putString("BCITY", "$editbCityString").apply()



            Log.d("TAG", "Я сохранил NAME $editNameString SNAME $editSameString BDATE $editBdateString BTIME $editBtimeString BCITY $editbCityString")
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)


                Log.d("TAG", "Ошибка сохранения")


            }

        binding.ButtonCancel.setOnClickListener {

         //  resources.updateConfiguration(oldConfig, resources.displayMetrics)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

        }

                binding.btnback.setOnClickListener {

       //             resources.updateConfiguration(oldConfig, resources.displayMetrics)
                  findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
              //     val lang = Locale.getDefault().language
              //      Log.d("TAG", "Lanig is $lang")
                }







    }

    }



