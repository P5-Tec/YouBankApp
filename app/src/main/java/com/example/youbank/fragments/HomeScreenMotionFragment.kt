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
import com.example.youbank.models.Account
import com.example.youbank.models.Card
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService
import com.example.youbank.room.CustomerViewModel
import com.example.youbank.room.RoomCustomer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenMotionFragment: Fragment() {

    private var _binding: FragmentHomeScreenMotionBinding? = null
    private val binding get() = _binding!!

    private val vm: CustomerViewModel by activityViewModels() // NEW WAY
    lateinit var cus: Customer
    lateinit var a: ArrayList<Account>
    lateinit var cards: ArrayList<Card>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cus = Customer()
        getCustomerDataWithApi(14)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentHomeScreenMotionBinding.inflate(inflater, container, false)


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun insertDataToDatabase() {
        val roomCustomer = RoomCustomer(0, cus.customerId, cus.fullName, cus.birthday, cus.email, cus.phone, cus.address)
        // Adding customer to roomdatabase
        vm.addCustomer(roomCustomer)

        vm.readCustomer.observe(viewLifecycleOwner, { c ->
            binding.header.text = cards[0].expirationDate.toString()
            binding.accountBoxHeader.text = cards[0].ccv.toString()
            binding.transactionBoxHeader.text = a[0].accountId.toString()
        })
    }

    private fun getCustomerDataWithApi(id: Int) {
        val service: CustomerService = ApiService.buildService(CustomerService::class.java)
        val req: Call<Customer> = service.getCustomerById(id)

        req.enqueue(object: Callback<Customer> {
            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                cus = response.body()!!
                a = response.body()!!.accounts
                cards = response.body()!!.accounts[0].cards

                Log.d("code", response.code().toString())

                Log.d("Customer name", cards.toString())

                insertDataToDatabase()
            }

            override fun onFailure(call: Call<Customer>, t: Throwable) {
                Log.d("get customer failed", t.cause.toString())
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