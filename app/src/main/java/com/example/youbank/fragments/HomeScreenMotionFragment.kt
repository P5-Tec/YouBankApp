package com.example.youbank.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.youbank.databinding.FragmentHomeScreenMotionBinding
import com.example.youbank.fragments.buttomModals.AccountSupportDialogFragment
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService
import com.example.youbank.room.CustomerViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenMotionFragment: Fragment() {

    private var _binding: FragmentHomeScreenMotionBinding? = null
    private val binding get() = _binding!!

    private val vm: CustomerViewModel by activityViewModels() // NEW WAY
    lateinit var c: Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentHomeScreenMotionBinding.inflate(inflater, container, false)

        getCustomerDataWithApi(14)


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun insertDataToDatabaser() {
        //val customerId =
    }

    private fun getCustomerDataWithApi(id: Int) {
        val service: CustomerService = ApiService.buildService(CustomerService::class.java)
        val req: Call<Customer> = service.getCustomerById(id)

        req.enqueue(object: Callback<Customer> {
            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                c = response.body()!!
                //binding.header.text = c.fullName
                Log.d("Customer name", c.fullName)
            }

            override fun onFailure(call: Call<Customer>, t: Throwable) {
                Log.d("get customer failed", t.message.toString())
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val db = Room.databaseBuilder(view.context, CustomerDatabase::class.java, "RoomDatabase").build()
        //val customerDao = db.customerDao()
        //val c = customerDao.getCustomer()

        binding.profileImg.setOnClickListener {
            AccountSupportDialogFragment.newInstance().show(childFragmentManager, "dialog")
        }
    }
}