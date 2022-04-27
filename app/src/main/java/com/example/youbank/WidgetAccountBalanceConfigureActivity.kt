package com.example.youbank

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.youbank.adapters.AccountAdapter
import com.example.youbank.databinding.WidgetAccountBalanceConfigureBinding
import com.example.youbank.viewModels.listViewModels.AccountListViewmodel
import android.widget.AdapterView
import android.view.Gravity
import android.widget.LinearLayout

abstract class WidgetAccountBalanceConfigureActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID
    private val model: AccountListViewmodel by viewModels()
//    private lateinit var accountAdapter: SpinnerAdapter
    private lateinit var binding: WidgetAccountBalanceConfigureBinding

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED)

        binding = WidgetAccountBalanceConfigureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener(onClickListener)

        // Find the widget id from the intent.
        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }






//        appWidgetText.setText(
//            loadTitlePref(
//                this@WidgetAccountBalanceConfigureActivity,
//                appWidgetId
//            )
//        )
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

//        model.allAccounts.observe(this) { it?.let { accountAdapter.submitList(it) } }

        val array = arrayOf("Java", "PHP", "Kotlin", "Javascript", "Python", "Swift")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, array)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding.accountList){
            adapter = arrayAdapter
            setSelection(0, false)
            prompt = "Select an account"
            gravity = Gravity.CENTER

        }

        val ll = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        ll.setMargins(10, 40, 10, 10)


//        binding.accountList.adapter = arrayAdapter

        return super.onCreateView(name, context, attrs)
    }

    private var onClickListener = View.OnClickListener {
        val context = this@WidgetAccountBalanceConfigureActivity

        // When the button is clicked, store the string locally
//        val widgetText = appWidgetText.text.toString()
//        saveTitlePref(context, appWidgetId, widgetText)

        // It is the responsibility of the configuration activity to update the app widget
        val appWidgetManager = AppWidgetManager.getInstance(context)
        updateAppWidget(context, appWidgetManager, appWidgetId)

        // Make sure we pass back the original appWidgetId
        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_OK, resultValue)
        finish()
    }
    override fun onItemSelected(p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: kotlin.Int, p3: kotlin.Long){TODO("Not yet implemented")
}

}

//private const val PREFS_NAME = "com.example.youbank.WidgetAccountBalance"
//private const val PREF_PREFIX_KEY = "appwidget_"
//
//// Write the prefix to the SharedPreferences object for this widget
//internal fun saveTitlePref(context: Context, appWidgetId: Int, text: String) {
//    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
//    prefs.putString(PREF_PREFIX_KEY + appWidgetId, text)
//    prefs.apply()
//}
//
//// Read the prefix from the SharedPreferences object for this widget.
//// If there is no preference saved, get the default from a resource
//internal fun loadTitlePref(context: Context, appWidgetId: Int): String {
//    val prefs = context.getSharedPreferences(PREFS_NAME, 0)
//    val titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null)
//    return titleValue ?: context.getString(R.string.appwidget_text)
//}
//
//internal fun deleteTitlePref(context: Context, appWidgetId: Int) {
//    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
//    prefs.remove(PREF_PREFIX_KEY + appWidgetId)
//    prefs.apply()
//}