package com.dandroid.whohavemymoney

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    private val editTextPrice by lazy{
        findViewById<EditText>(R.id.editTextPrice)
    }

    private val editTextPayment by lazy{
        findViewById<EditText>(R.id.editTextPayment)
    }

    private val editTextDate by lazy{
        findViewById<EditText>(R.id.editTextDate)
    }

    private var priceString = ""
    private var dateString = ""
    //TODO 홈 버튼 클릭시 캘린더 화면으로 이동
    //TODO 지불방식이 정해져 있지 않은 항목에 대해선 상단에 알림과 하단 메세지 클릭 시 해당 로우로 이동

    private fun getDateString(listDate:List<String>):String{
        if(listDate.size != 3){
            Toast.makeText(this, "날짜 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return "0000년 00월 00일"
        }

        return listDate[0]+"년 " +listDate[1]+"월 " +listDate[2]+"일"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //사용 날짜 초기화
        val today = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)
        val arrToday = today.split("-")
        editTextDate.setText(getDateString(arrToday))

        //사용 금액 입력 시 천원 단위 반점 표시
        editTextPrice.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.isNullOrEmpty() || s.toString()==priceString) return

                val priceWithoutComma = editTextPrice.text.toString().replace(",", "").toLong()
                val decimalformat = DecimalFormat("#,###")
                priceString = decimalformat.format(priceWithoutComma)
                editTextPrice.setText(priceString)
                //editTextPrice.setSelection(price.length) //선택한 위치에 커서가 계속 있어야지
            }
        })

        //TODO 지불 방식 선택
        editTextPayment.setOnClickListener {

            //TODO 마지막 입력 값 기억하기
        }

        // 사용 날짜 클릭 시 DatePicker Popup
        editTextDate.setOnClickListener {
            val cal = Calendar.getInstance()    //캘린더뷰 만들기
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                dateString = "${year}년 ${month+1}월 ${dayOfMonth}일"
                editTextDate.setText(dateString)
            }

            // EditText의 날짜가 기본값
            val date = editTextDate.text.toString()
            val year = date.substring(0, 4).toInt()
            val month = date.substring(date.indexOf("년")+1,date.indexOf("월")).trim().toInt()
            val day = date.substring(date.indexOf("월")+1,date.indexOf("일")).trim().toInt()
            cal.set(year,month -1,day)
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

}