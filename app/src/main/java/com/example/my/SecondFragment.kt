package com.example.my

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.my.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)



        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mySharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val Save_name: String = mySharedPreferences.getString("NAME", null).toString()
        val Save_sname: String = mySharedPreferences.getString("SNAME", null).toString()
        val Save_bdate: String = mySharedPreferences.getString("BDATE", null).toString()
        val Save_btime: String = mySharedPreferences.getString("BTIME", null).toString()
        val Save_bcity: String = mySharedPreferences.getString("BCITY", null).toString()

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

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

        }

                binding.btnback.setOnClickListener {

                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

                }







    }
    }



