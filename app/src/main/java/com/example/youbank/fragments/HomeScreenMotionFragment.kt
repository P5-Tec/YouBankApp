package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.youbank.databinding.FragmentHomeScreenMotionBinding
import com.example.youbank.fragments.buttomModals.AccountSupportDialogFragment
import com.example.youbank.room.viewmodels.CustomerViewModel
import com.example.youbank.viewModels.SharedPreferenceViewModel

class HomeScreenMotionFragment: Fragment() {

    private var _binding: FragmentHomeScreenMotionBinding? = null
    private val binding get() = _binding!!

    private val vm: CustomerViewModel by activityViewModels()
    private val spvm: SharedPreferenceViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeScreenMotionBinding.inflate(inflater, container, false)

        vm.readCustomer.observe(viewLifecycleOwner, { c ->
            if (c.customerId == spvm.getCustomerIdInSp()) { // User in room and user that logged in are the same
                // Do nothing
            }
            else { // User in room and user that logged in ARE NOT THE SAME

                // Clearing room of old user
                vm.deleteCustomer(c)

                // Adding new user to room
                vm.addCustomerToRoomDB(spvm.getCustomerIdInSp())
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileImg.setOnClickListener {
            AccountSupportDialogFragment.newInstance().show(childFragmentManager, "dialog")
        }

        vm.readCustomer.observe(viewLifecycleOwner, { room ->

            if (room.fullName.isNotBlank()) {
                binding.header.text = room.fullName
                binding.accountBoxHeader.text = room.birthday
                binding.transactionBoxHeader.text = room.address
            }
            else {
                binding.header.text = "room was empty"
                binding.accountBoxHeader.text = "room was empty"
                binding.transactionBoxHeader.text = "room was empty"
            }

        })

    }


    //private fun getCustomerDataWithApi(id: Int) {
    //    val service: CustomerService = ApiService.buildService(CustomerService::class.java)
    //    val req: Call<Customer> = service.getCustomerById(id)
    //
    //    req.enqueue(object: Callback<Customer> {
    //        override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
    //            cus = response.body()!! // Getting all the customer data
    //            a = response.body()!!.accounts // Getting all the account data
    //            cards = response.body()!!.accounts[0].cards // Getting all the card data
    //
    //            insertDataToDatabase()
    //        }
    //
    //        override fun onFailure(call: Call<Customer>, t: Throwable) {
    //            Log.d("get customer failed", t.cause.toString())
    //        }
    //    })
    //
    //}

    //private fun insertDataToDatabase() {
    //    val roomCustomer = RoomCustomer(0, cus.customerId, cus.fullName, cus.birthday, cus.email, cus.phone, cus.address)
    //    // Adding customer to roomdatabase
    //    vm.addCustomer(roomCustomer)
    //
    //    vm.readCustomer.observe(viewLifecycleOwner, { c ->
    //        binding.header.text = cards[0].expirationDate
    //        binding.accountBoxHeader.text = cards[0].ccv.toString()
    //        binding.transactionBoxHeader.text = a[0].accountId.toString()
    //    })
    //}

}