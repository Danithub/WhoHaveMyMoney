package com.dandroid.whohavemymoney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val editTextPrice by lazy{
        findViewById<EditText>(R.id.editTextPrice)
    }

    private val editTextPayment by lazy{
        findViewById<EditText>(R.id.editTextPayment)
    }

    private var price = ""

    //TODO 사용 날짜 클릭 시 DatePicker or 숫자입력
    //TODO 홈 버튼 클릭시 캘린더 화면으로 이동
    //TODO 지불방식이 정해져 있지 않은 항목에 대해선 상단에 알림과 하단 메세지 클릭 시 해당 로우로 이동

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //사용 금액 입력 시 천원 단위 반점 표시
        editTextPrice.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.isNullOrEmpty() || s.toString()==price) return

                val priceWithoutComma = editTextPrice.text.toString().replace(",", "").toLong()
                val decimalformat = DecimalFormat("#,###")
                price = decimalformat.format(priceWithoutComma)
                editTextPrice.setText(price)
                //editTextPrice.setSelection(price.length) //선택한 위치에 커서가 계속 있어야지
            }
        })

        //TODO 지불 방식 선택
        editTextPayment.setOnClickListener {

            //TODO 마지막 입력 값 기억하기
        }
    }

}