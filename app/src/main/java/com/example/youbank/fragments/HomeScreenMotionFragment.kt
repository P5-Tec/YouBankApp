package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.youbank.databinding.FragmentHomeScreenMotionBinding
import com.example.youbank.fragments.buttomModals.AccountSupportDialogFragment
import com.example.youbank.room.AccountViewModel
import com.example.youbank.room.CustomerViewModel

class HomeScreenMotionFragment: Fragment() {

    private var _binding: FragmentHomeScreenMotionBinding? = null
    private val binding get() = _binding!!

    private val vm: CustomerViewModel by activityViewModels()
    private val vma: AccountViewModel by activityViewModels()
    //private val vmc: CardViewModel by activityViewModels()

    //lateinit var cus: Customer
    //lateinit var a: ArrayList<Account>
    //lateinit var cards: ArrayList<Card>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //cus = Customer()
        //getCustomerDataWithApi(14)
        vm.addCustomer(14)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentHomeScreenMotionBinding.inflate(inflater, container, false)

        vm.readCustomer.observe(viewLifecycleOwner, { rc ->
            binding.header.text = rc.fullName
        })
        vma.readAccounts.observe(viewLifecycleOwner, { ra ->
            binding.accountBoxHeader.text = ra[0].accountNumber
            binding.transactionBoxHeader.text = ra[0].accountId.toString()
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileImg.setOnClickListener {
            AccountSupportDialogFragment.newInstance().show(childFragmentManager, "dialog")
        }
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