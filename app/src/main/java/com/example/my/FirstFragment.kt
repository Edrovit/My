package com.example.my

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.my.databinding.FragmentFirstBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.ktor.client.*
import io.ktor.client.response.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.*




/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editName1 = view.findViewById<TextView>(R.id.textview_first)
        editName1.visibility = View.INVISIBLE
        val spinner = view.findViewById<ProgressBar>(R.id.progressBar)
        spinner.setVisibility(View.INVISIBLE);
        val editName2 = view.findViewById<TextView>(R.id.textview_data)
        editName2.visibility = View.INVISIBLE

        val bottomSheet = view.findViewById<ConstraintLayout>(R.id.bottom_sheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)


        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED




        val button = view.findViewById<Button>(R.id.button_first)

        button.setOnClickListener {


            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            val mySharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

            val Curl_name: String = mySharedPreferences.getString("NAME", null).toString()
            val Curl_sname: String = mySharedPreferences.getString("SNAME", null).toString()
            val Curl_bdate: String = mySharedPreferences.getString("BDATE", null).toString()
            val Curl_btime: String = mySharedPreferences.getString("BTIME", null).toString()
            val Curl_bcity: String = mySharedPreferences.getString("BCITY", null).toString()

            Log.d("TAG", "Я прочитал $Curl_name, $Curl_sname, $Curl_bdate, $Curl_btime, $Curl_bcity")


            val url = "http://3.71.201.192/?lang=Russian&days=7&gender=male&name=$Curl_name&sirname=$Curl_sname&birthday=$Curl_bdate&time=$Curl_btime&city=$Curl_bcity"

            //val client = HttpClient()
         //   val response: HttpResponse = client.get("$url")

            suspend fun fetchData(url: String): String {
                val client = HttpClient()
                try {
                    val response = client.get<String>(url)
                    return response
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                } finally {
                    client.close()

                }
                return ""


                }

            GlobalScope.launch(Dispatchers.Main) {


                val spinner = view.findViewById<ProgressBar>(R.id.progressBar)
                spinner.setVisibility(View.VISIBLE);

                val result = fetchData(url)
                val editName = view.findViewById<TextView>(R.id.textview_first)
                val editName2 = view.findViewById<TextView>(R.id.textview_data)

                editName?.setText("$result ")
                spinner.setVisibility(View.INVISIBLE);
                editName?.visibility = View.VISIBLE
                editName2.visibility = View.VISIBLE
            }

         //   findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.btnsetting.setOnClickListener {

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}