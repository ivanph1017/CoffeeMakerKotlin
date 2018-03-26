package com.eveexite.coffeemaker.presentation.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.eveexite.coffeemaker.R
import com.eveexite.coffeemaker.presentation.base.BaseActivity
import com.eveexite.coffeemaker.presentation.main.model.AnimUi
import com.eveexite.coffeemaker.presentation.main.model.InfoUi
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Ivan on 29/01/2018.
 *
 */

class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadCoffeeMaker()

        vMain.setAnimation("coffee_maker.json")

        viewModel.getInfoLive().observe(this,  infoChanges)
        viewModel.getStatusTextLive().observe(this, statusTextChanges)
        viewModel.getAnimLive().observe(this, animChanges)

        fabPower.setOnClickListener { _ -> viewModel.checkAction() }

        viewModel.getMsgLive().observe(this, msgChanges)
        viewModel.getMsgConfirmLive().observe(this, msgConfirmChanges)
        viewModel.getMsgFinishLive().observe(this, msgFinishChanges)
    }


    override fun getLayout() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    private val infoChanges = Observer<InfoUi> {
        it?.let {
            vWaterLevel.waterLevel = it.waterLevel
            tvTimer.text = it.timer
        }
    }

    private val statusTextChanges = Observer<String> {
        it?.let {
            vTitle.text = it
            vTitle.invalidate()
        }
    }

    private val animChanges = Observer<AnimUi> {
        it?.let {
            vMain.clearAnimation()
            vMain.setAnimation(it.fileUri)
            vMain.progress = 0.0F
            if (it.canPlayAnim) vMain.playAnimation()
        }
    }

    private val msgChanges = Observer<Pair<String, String>> {
        it?.let {
            showMessage(it.first, it.second, {})
        }
    }

    private val msgConfirmChanges = Observer<Pair<String, String>> {
        it?.let {
            showMsgConfirm(it.first, it.second)
        }
    }

    private val msgFinishChanges = Observer<Pair<String, String>> {
        it?.let {
            showMessage(it.first, it.second, { finishAffinity() })
        }
    }

    private fun <Unit> showMessage(title: String, message: String, callback: () -> Unit) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val view: View = layoutInflater.inflate(R.layout.dialog_alert, null)
        view.findViewById<TextView>(R.id.tvWindowTitle).text = title
        view.findViewById<TextView>(R.id.tvMessage).text = message
        val alertDialog: AlertDialog = builder.setView(view).create()
        view.findViewById<Button>(R.id.btnDismiss).setOnClickListener { _ ->
            alertDialog.dismiss()
            kotlin.run(callback)
        }
        alertDialog.show()
    }

    private fun showMsgConfirm(title: String, message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val view: View = layoutInflater.inflate(R.layout.dialog_alert_confirmation, null)
        view.findViewById<TextView>(R.id.tvWindowTitle).text = title
        view.findViewById<TextView>(R.id.tvMessage).text = message
        val alertDialog: AlertDialog = builder.setView(view).create()
        view.findViewById<Button>(R.id.btnAccept).setOnClickListener { _ ->
            alertDialog.dismiss()
            viewModel.checkCoffeeMakerStatus()
        }
        view.findViewById<Button>(R.id.btnCancel).setOnClickListener { _ -> alertDialog.cancel() }
        alertDialog.show()
    }

}
