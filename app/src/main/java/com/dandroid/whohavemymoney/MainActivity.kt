package com.dandroid.whohavemymoney

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
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

    private val editTextPlace by lazy{
        findViewById<EditText>(R.id.editTextPlace)
    }

    private val editTextDate by lazy{
        findViewById<EditText>(R.id.editTextDate)
    }

    private val buttonHome by lazy{
        findViewById<Button>(R.id.buttonHome)
    }

    private val buttonClear by lazy{
        findViewById<Button>(R.id.buttonClear)
    }

    private val spinnerPayment by lazy{
        findViewById<Spinner>(R.id.spinnerPayment)
    }

    private var priceString = ""
    private var dateString = ""

    //TODO 거래이력 구분하여 조회 및 저장 구현
    //TODO 홈(리스트) 화면 (지출, 수입, 이체 선택해서 해당 항목을 리스트로 보여줌.)
    //TODO 지불방식이 정해져 있지 않은 항목에 대해선 상단에 알림과 하단 메세지 클릭 시 해당 로우로 이동

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //사용 날짜 초기화
        val today = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)
        val arrToday = today.split("-")
        editTextDate.setText(getDateString(arrToday))

        editTextDate.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (hasFocus) {
                    //  .. 포커스시
                } else {
                    //  .. 포커스 뺏겼을 때
                    // TODO 키보드 숨기기
                    view.hideKeyboard()
                }
            }
        })

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

        //지불 방식 선택
        val payment = resources.getStringArray(R.array.PaymentList)

        spinnerPayment.adapter = ArrayAdapter.createFromResource(this, R.array.PaymentList, R.layout.support_simple_spinner_dropdown_item)

        //TODO 지불 방식 추가 제거
        //TODO 마지막 입력 값 기억하기

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

        // 초기화 버튼
        buttonClear.setOnClickListener{

            // 지불 방식

            // 사용처
            editTextPlace.setText("")

            // 금액
            editTextPrice.setText("0")

            // 사용일자
            val today = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)
            val arrToday = today.split("-")
            editTextDate.setText(getDateString(arrToday))
        }

        buttonHome.setOnClickListener {
            val homeIntent = Intent(this, CalendarActivity::class.java)
            startActivity(homeIntent)
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun getDateString(listDate:List<String>):String{
        if(listDate.size != 3){
            Toast.makeText(this, "날짜 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return "0000년 00월 00일"
        }

        return listDate[0]+"년 " +listDate[1]+"월 " +listDate[2]+"일"
    }
}