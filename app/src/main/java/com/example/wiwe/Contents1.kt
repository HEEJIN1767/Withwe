package com.example.wiwe

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TabHost
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.wiwe.databinding.ActivityContents1Binding

@Suppress("deprecation")
class Contents1 : AppCompatActivity() {

    private lateinit var binding: ActivityContents1Binding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContents1Binding.inflate(layoutInflater)
        setContentView(binding.root)



        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()


        // 스피너 설정
        val spinner = binding.spinner
        ArrayAdapter.createFromResource(
            this, R.array.information, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                when (position) {
                    0 -> {


                        binding.titleTv1.setText("여성에게 좋은 생활 습관")



                        binding.contentTv1.setText("1. 규칙적인 운동")
                        binding.contentTv1b.setVisibility(View.GONE)
                        binding.contentTv1c.setVisibility(View.GONE)
                        binding.contentTv1a.setText("-꾸준한 운동은 체중 관리 체력 향상 스트레스 감소 그리고 만성질환 예방에 도움이 된다.\n-주 2~3회, 하루 30분 이상의 중간 강도 운동이 권장되며, 걷기, 수영, 자전거 타기, 요가, 필라테스 등 다양한 운동을 선택할 수 있다.")
                        binding.contentTv1.setOnClickListener {
                            binding.linearLayout1.setVisibility(View.VISIBLE)
                          }
                        binding.cancel1.setOnClickListener {
                            binding.linearLayout1.setVisibility(View.GONE)
                           }

                        binding.contentTv2.setText("2. 충분한 수면")
                        binding.contentTv2b.setVisibility(View.GONE)
                        binding.contentTv2c.setVisibility(View.GONE)
                        binding.contentTv2d.setVisibility(View.GONE)
                        binding.contentTv2a.setText("-여성들은 하루 최소 7~8시간의 수면이 필요하다.\n-수면 부족은 스트레스 증가, 심장질환, 당뇨병 등의 위험을 높일 수 있다.")
                        binding.contentTv2.setOnClickListener {binding.linearLayout2.setVisibility(View.VISIBLE) }
                        binding.cancel2.setOnClickListener { binding.linearLayout2.setVisibility(View.GONE) }

                        binding.contentTv3.setText("3. 스트레스 관리")
                        binding.contentTv3b.setVisibility(View.GONE)
                        binding.contentTv3c.setVisibility(View.GONE)
                        binding.contentTv3d.setVisibility(View.GONE)
                        binding.contentTv3a.setText("-스트레스는 신체와 정신 건강에 부정적인 영향을 미친다.\n-여성들은 정기적인 휴식과 이완기법(깊은 숨쉬기, 명상, 아로마 테라피 등)을 통해 스트레스를 관리해야 한다.")
                        binding.contentTv3.setOnClickListener{binding.linearLayout3.setVisibility(View.VISIBLE) }
                        binding.cancel3.setOnClickListener{ binding.linearLayout3.setVisibility(View.GONE) }

                        binding.contentTv4.setText("4. 건강한 식습관")
                        binding.contentTv4b.setVisibility(View.GONE)
                        binding.contentTv4c.setVisibility(View.GONE)
                        binding.contentTv4a.setText("-여성들은 과일 채소, 통곡류, 콩, 견과류 등의 영양소가 풍부한 음식품을 섭취해야 한다.\n-또한 과도한 카페인 설탕, 소금, 가공식품 섭취를 피해야 한다.")
                        binding.contentTv4.setOnClickListener {binding.linearLayout4.setVisibility(View.VISIBLE) }
                        binding.cancel4.setOnClickListener { binding.linearLayout4.setVisibility(View.GONE) }

                        binding.contentTv5.setText("5. 금연 및 음주")
                        binding.contentTv5b.setVisibility(View.GONE)
                        binding.contentTv5c.setVisibility(View.GONE)
                        binding.contentTv5.setVisibility(View.VISIBLE)
                        binding.contentTv5a.setText("-여성들은 건강을 위해 흡연을 피하고 음주를 적절한 선에서 관리해야 한다.")
                        binding.contentTv5.setOnClickListener {binding.linearLayout5.setVisibility(View.VISIBLE) }
                        binding.cancel5.setOnClickListener{ binding.linearLayout5.setVisibility(View.GONE) }

                        binding.contentTv6.setText("6. 정기적인 건강검진")
                        binding.contentTv6b.setVisibility(View.GONE)
                        binding.contentTv6c.setVisibility(View.GONE)
                        binding.contentTv6.setVisibility(View.VISIBLE)
                        binding.contentTv6a.setText("-여성들은 정기적으로 건강검진을 받아야 한다.\n-이를 통해 질환을 조기에 발견하고, 적절한 치료와 관리를 받을 수 있다.\n-여성 건강과 관련된 검진 항목으로는 유방암, 자궁경부암, 골밀도 검사 등이 있다.")
                        binding.contentTv6.setOnClickListener {binding.linearLayout6.setVisibility(View.VISIBLE) }
                        binding.cancel6.setOnClickListener { binding.linearLayout6.setVisibility(View.GONE) }

                        binding.contentTv7.setText("7. 호르몬 밸런스 유지")
                        binding.contentTv7b.setVisibility(View.GONE)
                        binding.contentTv7c.setVisibility(View.GONE)
                        binding.contentTv7.setVisibility(View.VISIBLE)
                        binding.contentTv7a.setText("-여성들은 스트레스 관리, 건강한 식습관, 규칙적인 운동을 통해 호르몬 밸런스를 유지해야 한다.\n-호르몬 불균형은 여성 건강에 여러 가지 문제를 야기할 수 있으므로 이를 조절하는 것이 중요하다. ")
                        binding.contentTv7.setOnClickListener {binding.linearLayout7.setVisibility(View.VISIBLE) }
                        binding.cancel7.setOnClickListener { binding.linearLayout7.setVisibility(View.GONE) }

                        binding.contentTv8.setText("8. 성생활관리")
                        binding.contentTv8b.setVisibility(View.GONE)
                        binding.contentTv8c.setVisibility(View.GONE)
                        binding.contentTv8.setVisibility(View.VISIBLE)
                        binding.contentTv8a.setText("-여성들은 안전한 성관계를 통해 성병 감염의 위험을 줄일 수 있다.\n-또한, 정기적인 자궁경부암 검진을 통해 조기 발견 및 치료를 받을 수 있다.")
                        binding.contentTv8.setOnClickListener {binding.linearLayout8.setVisibility(View.VISIBLE) }
                        binding.cancel8.setOnClickListener{ binding.linearLayout8.setVisibility(View.GONE) }

                        binding.contentTv9.setText("9. 정신건강 관리")
                        binding.contentTv9b.setVisibility(View.GONE)
                        binding.contentTv9c.setVisibility(View.GONE)
                        binding.contentTv9a.setText("-여성들은 정신건강을 유지하기 위해 스트레스 관리, 충분한 수면, 취미활동, 사회활동 등을 통해 정신 건강을 챙겨야 한다.\n-우울증, 불안장애 등의 정신건강 문제 발생할 경우 적절한 치료와 상담을 받는 것이 중요하다.")
                        binding.contentTv9.setOnClickListener{binding.linearLayout9.setVisibility(View.VISIBLE) }
                        binding.cancel9.setOnClickListener{ binding.linearLayout9.setVisibility(View.GONE) }

                        binding.contentTv10.setText("10. 건강한 체중유지")
                        binding.contentTv10b.setVisibility(View.GONE)
                        binding.contentTv10c.setVisibility(View.GONE)
                        binding.contentTv10a.setText("-체중조절은 여성건강에 큰 영향을 미친다. 건강한 체중을 유지하기 위해 규칙적인 운동과 건강한 식습관을 유지해야 한다.\n-과체중이나 비만은 당뇨병, 심장질환, 관절 문제 등에 만성질환을 일으킬 수 있으므로 적절한 체중 관리가 필요하다.")
                        binding.contentTv10.setOnClickListener {binding.linearLayout10.setVisibility(View.VISIBLE) }
                        binding.cancel10.setOnClickListener { binding.linearLayout10.setVisibility(View.GONE) }

                        binding.contentTv9.setVisibility(View.VISIBLE)
                        binding.contentTv10.setVisibility(View.VISIBLE)
                        binding.titleTv2.setVisibility(View.GONE)
                        binding.viewTv5.setVisibility(View.VISIBLE)
                        binding.viewTv4.setVisibility(View.GONE)
                        binding.ivItem2.setVisibility(View.GONE)
                        binding.viewTv6.setVisibility(View.VISIBLE)
                        binding.viewTv7.setVisibility(View.VISIBLE)
                        binding.viewTv8.setVisibility(View.VISIBLE)
                        binding.viewTv9.setVisibility(View.VISIBLE)
                        binding.viewTv10.setVisibility(View.VISIBLE)

                        binding.ivItem5.setVisibility(View.GONE)
                        binding.ivItem4.setVisibility(View.GONE)
                        binding.ivItem3.setVisibility(View.GONE)
                        binding.ivItem2.setVisibility(View.GONE)
                        binding.ivItem1.setVisibility(View.VISIBLE)

                        binding.viewIv1.setVisibility(View.GONE)
                        binding.viewIv2.setVisibility(View.GONE)
                        binding.viewIv3.setVisibility(View.GONE)
                        binding.viewIv4.setVisibility(View.GONE)
                        binding.viewIv5.setVisibility(View.GONE)
                        binding.viewIv6.setVisibility(View.GONE)
                        binding.viewIv7.setVisibility(View.GONE)
                        binding.viewIv8.setVisibility(View.GONE)
                    }
                    1 -> {
                        binding.titleTv1.setText("여성에게 필요한 영양소")

                        binding.contentTv1.setText("1. 마그네슘")
                        binding.contentTv1c.setVisibility(View.GONE)
                        binding.contentTv1a.setText("-마그네슘은 뼈 건강에 대표적인 영양소인 칼슘과 함께 작용하기 때문에 갱년기 여성의 질환 중 하나인 골다공증 예방을 위해서 마그네슘을 섭취 하는 것이 좋습니다.")
                        binding.contentTv1b.setText("음식 - 다시마, 아몬드, 해바라기 씨")
                        binding.contentTv1b.setVisibility(View.VISIBLE)
                        binding.contentTv1.setOnClickListener {binding.linearLayout1.setVisibility(View.VISIBLE) }
                        binding.cancel1.setOnClickListener { binding.linearLayout1.setVisibility(View.GONE) }

                        binding.contentTv2.setText("2. 칼슘")
                        binding.contentTv2c.setVisibility(View.GONE)
                        binding.contentTv2d.setVisibility(View.GONE)
                        binding.contentTv2b.setText("음식 - 두부, 시리얼, 콩, 브로콜리, 생선")
                        binding.contentTv2b.setVisibility(View.VISIBLE)
                        binding.contentTv2a.setText("-폐경에 가까워질수록 여성들의 뼈세포가 줄어드는데 뼈세포의 생성을 도와주는 식품이 바로 칼슘입니다.\n-꾸준히 칼슘을 섭취 하는 것은 골다공증 위험을 줄일 수 있습니다.")
                        binding.contentTv2.setOnClickListener {binding.linearLayout2.setVisibility(View.VISIBLE) }
                        binding.cancel2.setOnClickListener { binding.linearLayout2.setVisibility(View.GONE) }

                        binding.contentTv3.setText("3. 엽산")
                        binding.contentTv3c.setVisibility(View.GONE)
                        binding.contentTv3d.setVisibility(View.GONE)
                        binding.contentTv3b.setText("음식 - 잎사귀가 많은 채소류, 감귤류, 호박, 베리류, 견과류, 올리브오일")
                        binding.contentTv3b.setVisibility(View.VISIBLE)
                        binding.contentTv3a.setText("-엽산은 임신 중에 필수적인 영양분으로 많이 알려져 있지만 갱년기 여성에게도 꼭 필요한 영양소 입니다.\n-엽산이 부족하면 빈혈, 두통, 체중감소, 체력저하, 세포와 적혈구 수를 감소시켜 피로감, 구강염증, 모발, 손톱, 피부 등의 색상 변화 등을 일으키는 것을 예방할 수 있습니다.")
                        binding.contentTv3.setOnClickListener {binding.linearLayout3.setVisibility(View.VISIBLE) }
                        binding.cancel3.setOnClickListener{ binding.linearLayout3.setVisibility(View.GONE) }

                        binding.contentTv4.setText("4. 식이섬유")
                        binding.contentTv4c.setVisibility(View.GONE)
                        binding.contentTv4b.setText("음식 - 강낭콩, 병아리콩, 검은콩, 보리, 귀리, 아보카도, 배")
                        binding.contentTv4b.setVisibility(View.VISIBLE)
                        binding.contentTv4a.setText("-노화가 오면 특히 식이섬유가 들어 있는 식품을 많이 섭취하는 것이 좋습니다.\n-65세 이상의 여성에게 흔히 나타나는 변비는 활동량은 줄고 고열량의 식사가 증가했을 때 나타나는 것으로 섬유질이 풍부한 식단으로 배변을 촉진할 수 있도록 해야 합니다.\n-또한 식이섬유를 많이 섭취할수록 우울증 발병 위험도 낮출 수 있습니다. ")
                        binding.contentTv4.setOnClickListener {binding.linearLayout4.setVisibility(View.VISIBLE) }
                        binding.cancel4.setOnClickListener { binding.linearLayout4.setVisibility(View.GONE) }

                        binding.contentTv5.setText("5. 단백질")
                        binding.contentTv5c.setVisibility(View.GONE)
                        binding.contentTv5b.setText("음식 - 계란, 흰 살 생선, 검은콩, 참치 통조림, 귀리, 요거트")
                        binding.contentTv5b.setVisibility(View.VISIBLE)
                        binding.contentTv5a.setText("-노년기에 접어 들수록 근육생성 효율과 단백질 흡수율이 떨어져 일반 성인들보다 더 단백질 섭취에 신경써야 합니다.\n-단백질 섭취를 통해 근육을 유지해야 근육 감소증을 예방할 수 있고 근육량이 떨어지면서 살이 찌기 쉬운 체질로 변화하는 것을 막을 수 있습니다.")
                        binding.contentTv5.setOnClickListener {binding.linearLayout5.setVisibility(View.VISIBLE) }
                        binding.cancel5.setOnClickListener { binding.linearLayout5.setVisibility(View.GONE) }

                        binding.contentTv6.setText("6. 항산화 비타민")
                        binding.contentTv6b.setVisibility(View.VISIBLE)
                        binding.contentTv6c.setVisibility(View.GONE)
                        binding.contentTv6b.setText("음식 - 비타민C 녹색잎 채소, 파슬리, 고수, 블랙베리, 라즈베리, 딸기\n비타민E 아몬드, 해바라기 씨, 땅콩 등 견과류, 식물성 기름")
                        binding.contentTv6a.setText("-갱년기에는 호르몬 변화로 인해 수면장애와 피로감이 급증하는데 이를 감소시킵니다.\n-강력한 항산화 물질인 비타민C와 비타민E를 섭취하면 세포의 노화를 막아주고 우리 몸이 더 건강해질 수 있습니다.")
                        binding.contentTv6.setOnClickListener {binding.linearLayout6.setVisibility(View.VISIBLE) }
                        binding.cancel6.setOnClickListener { binding.linearLayout6.setVisibility(View.GONE) }

                        binding.contentTv7.setText("7. 비타민B12")
                        binding.contentTv7b.setVisibility(View.VISIBLE)
                        binding.contentTv7c.setVisibility(View.GONE)
                        binding.contentTv7b.setText("음식 - 생선, 살코기, 달걀, 우유")
                        binding.contentTv7a.setText("-뼈의 건강, DNA생산, 적혈구 생산 및 신경의 원활한 기능을 위해 필요합니다.\n-나이가 들어갈수록 비타민B12를 흡수하는 능력이 떨어져 결핍의 위험성이 높아지고 있습니다.\n-비타민B12가 부족하면 빈혈에 걸릴 위험이 커지며 기억력 감퇴나 우울증을 예방할 수 있습니다.")
                        binding.contentTv7.setOnClickListener {binding.linearLayout7.setVisibility(View.VISIBLE) }
                        binding.cancel7.setOnClickListener { binding.linearLayout7.setVisibility(View.GONE) }

                        binding.contentTv8.setText("8. 비타민D")
                        binding.contentTv8b.setVisibility(View.VISIBLE)
                        binding.contentTv8c.setVisibility(View.GONE)
                        binding.contentTv8b.setText("음식 - 유제품")
                        binding.contentTv8a.setText("-나이가 있는 여성이 충분히 섭취하지 못하면 뼈가 약해지고 골다공증에 걸리게 됩니다.\n-비타민D 결핍률은 약 81.4%에 달하며 국내 폐경기 여성 중 약 90.6%가 비타민D 결핍군에 속한다고 합니다.\n-비타민D는 주로 햇빛을 통해 얻어지는데 나이가 들수록 활동성이 줄어들기 때문에 유제품을 하루 226g 정도 섭취하여 비타민D를 보충하는 것이 좋습니다.")
                        binding.contentTv8.setOnClickListener {binding.linearLayout8.setVisibility(View.VISIBLE) }
                        binding.cancel8.setOnClickListener{ binding.linearLayout8.setVisibility(View.GONE) }

                        binding.contentTv5.setVisibility(View.VISIBLE)
                        binding.contentTv6.setVisibility(View.VISIBLE)
                        binding.contentTv7.setVisibility(View.VISIBLE)
                        binding.contentTv8.setVisibility(View.VISIBLE)
                        binding.contentTv9.setVisibility(View.GONE)
                        binding.contentTv10.setVisibility(View.GONE)
                        binding.titleTv2.setVisibility(View.GONE)
                        binding.linearLayout9.setVisibility(View.GONE)
                        binding.linearLayout10.setVisibility(View.GONE)
                        binding.viewTv9.setVisibility(View.GONE)
                        binding.viewTv10.setVisibility(View.GONE)
                        binding.viewTv5.setVisibility(View.VISIBLE)
                        binding.viewTv4.setVisibility(View.GONE)
                        binding.viewTv6.setVisibility(View.VISIBLE)
                        binding.viewTv7.setVisibility(View.VISIBLE)
                        binding.viewTv8.setVisibility(View.VISIBLE)

                        binding.ivItem5.setVisibility(View.GONE)
                        binding.ivItem4.setVisibility(View.GONE)
                        binding.ivItem3.setVisibility(View.GONE)
                        binding.ivItem1.setVisibility(View.GONE)
                        binding.ivItem2.setVisibility(View.VISIBLE)

                        binding.viewIv1.setVisibility(View.VISIBLE)
                        binding.viewIv2.setVisibility(View.VISIBLE)
                        binding.viewIv3.setVisibility(View.VISIBLE)
                        binding.viewIv4.setVisibility(View.VISIBLE)
                        binding.viewIv5.setVisibility(View.VISIBLE)
                        binding.viewIv6.setVisibility(View.VISIBLE)
                        binding.viewIv7.setVisibility(View.VISIBLE)
                        binding.viewIv8.setVisibility(View.VISIBLE)
                    }
                    2 -> {
                        binding.titleTv1.setText("여성 질환")

                        binding.contentTv1.setText("1. 질염")
                        binding.contentTv1c.setVisibility(View.VISIBLE)
                        binding.contentTv1a.setText("질염은 질의 염증 상태를 이르는 말로 감염에 의한 질염 폐경 이후 질 점막이 얇아져서 생기는 위축성 질염 등이 있습니다. 감염에 의한 질염은 원인에 따라 칸디다 질염, 세균성 질염, 트리코모나스 질염 등으로 나뉩니다 그중 가장 흔히 발생하는 것은 칸디다 질염으로 여성의 75%가 평생 적어도 한 번은 경험한다고 합니다. 세균성 질염은 정상적으로 질 내에 살면서 질을 산성으로 유지하는 락토바실러스라는 유산균이 없어지고 혐기성 세균이 증식하면서 발생 하는 염증입니다. 마지막으로 트리코모나스 질염은 트리코모나스라는 원충에 의해 감염되는데 성관계로 전파되기 때문에 성매개질환 범주에 포함되며 남녀가 함께 치료를 받아야 합니다.")
                        binding.contentTv1b.setText("증상 - 외음부, 질입구가 가렵거나 부어오름, 질 분비물의 색으로 증상을 나타낼 수 있습니다. \n세균성 질염이나 트리코모나스 질염은 무증상인 경우도 있으니 주기적인 건강검진을 받는 것이 좋습니다.")
                        binding.contentTv1c.setText("완화 및 예방 - 나일론이나 합성섬유 소재보다는 면소재의 속옷을 입는다.\n" +
                                "질 내부를 너무 자주 씻어내지 않는다.\n" +
                                "항생제를 과용하지 않는다.\n")
                        binding.contentTv1b.setVisibility(View.VISIBLE)
                        binding.contentTv1.setOnClickListener {binding.linearLayout1.setVisibility(View.VISIBLE) }
                        binding.cancel1.setOnClickListener { binding.linearLayout1.setVisibility(View.GONE) }

                        binding.contentTv2.setText("2. 자궁근종")
                        binding.contentTv2c.setVisibility(View.VISIBLE)
                        binding.contentTv2d.setVisibility(View.VISIBLE)
                        binding.contentTv2a.setText("자궁근종은 자궁을 대부분 이루고 있는 평활근에 생기는 종양이며 양성 질환입니다. \n자궁근종은 여성에게 매우 흔하게 발생하는 질병이면 35세 이상의 여성의 40~50% 에서 나타납니다.")
                        binding.contentTv2b.setText("증상 - 증상이 없는 경우가 절반 정도 되고 증상이 있는 경우에는 자궁근종의 위치나 크기에 따라 다양한 증상이 생길 수 있습니다. \n생리과다가 가장 흔한 증상이며 골반 통증 생리통 성교시 동증 골반 압박감 빈뇨 등이 나타날 수도 있습니다.")
                        binding.contentTv2c.setText("진단/검사 - 골반 내진으로도 자궁이 커진 것을 알 수 있지만 대게 초음파 검사로 진단하게 됩니다. ")
                        binding.contentTv2d.setText("치료 - 크게 약물적 치료와 수술적 치료로 나뉘는데 환자의 연령, 폐경 여부, 증상 유무, 환자의 선호도에 따라 치료방법을 선택할 수 있습니다. \n약물적 치료에는 호르몬 주사가 있습니다. \n호르몬 주사는 일시적이므로 치료가 끝나면 다시 근종의 크기가 커질 수 있고 여성호르몬의 감소에 따른 부작용이 있을 수도 있습니다. \n수술적 방법으로는 자궁절제술이 있고 자궁을 보존하고자 할 때에는 근종 적출술을 시행할 수 있습니다. ")
                        binding.contentTv2b.setVisibility(View.VISIBLE)
                        binding.contentTv2.setOnClickListener {binding.linearLayout2.setVisibility(View.VISIBLE) }
                        binding.cancel2.setOnClickListener { binding.linearLayout2.setVisibility(View.GONE) }

                        binding.contentTv3.setText("3. 난소 종양")
                        binding.contentTv3c.setVisibility(View.VISIBLE)
                        binding.contentTv3d.setVisibility(View.VISIBLE)
                        binding.contentTv3a.setText("난소는 인체의 장기 중에서 가장 많은 종류의 종양이 발생하는 기관으로 이곳에 생긴 종양을 통틀어 난소 종양이라고 합니다. \n난소 종양은 양손 종양과 악성 종양 그 사이의 중간인 경계성 종양까지 세 가지로 나눌 수 있습니다.")
                        binding.contentTv3b.setText("증상 - 난소 종양의 크기가 작을 때는 증상이 없지만 종양의 크기가 커지면 주변 장기를 압박하며 소화불량이나 아랫배의 통증 등이 나타날 수 있습니다. \n종양이 악성인 경우에는 병이 진행되며 자궁출혈과 구토 등의 증상이 생깁니다.")
                        binding.contentTv3c.setText("진단/검사 - 난소종양은 초음파, CT, MRI 등으로 진단할 수 있습니다.")
                        binding.contentTv3d.setText("치료 - 양성 종양이라도 크기가 팔 센티 이상으로 큰 경우 종양이 꼬여 염전이 생겼거나 종양이 파열된 경우에는 수술로 종양을 제거합니다 악성 종양과 경계성 종양의 경우에는 수술로 종양을 제거한 뒤에 항암화학요법과 방사선치료를 병행하게 됩니다. ")
                        binding.contentTv3b.setVisibility(View.VISIBLE)
                        binding.contentTv3.setOnClickListener {binding.linearLayout3.setVisibility(View.VISIBLE) }
                        binding.cancel3.setOnClickListener { binding.linearLayout3.setVisibility(View.GONE) }

                        binding.contentTv4.setText("4. 자궁경부암")
                        binding.contentTv4c.setVisibility(View.VISIBLE)
                        binding.contentTv4a.setText("질에 연결된 자궁경부에 발생하는 악성 종양입니다. \n자궁경부암은 전 세계적으로 여성에게 발병하는 암 중 2번째 로 흔한 암이며 우리나라의 경우 스크리닝 검사와 자궁경부암 백신 접종으로 인해 그 발생률이 매년 지속적으로 감소하고 있습니다. \n인유두종 바이러스 감염이 주된 원인입니다.")
                        binding.contentTv4b.setText("증상 - 대부분 선교 후 경미한 질 출혈이 가장 흔한 증상입니다. \n처음에는 피가 묻어 나오는 정도이지만 암이 진행되면서 출혈 및 질분비물이 증가하고 궤양이 심화됩니다. \n이차 감염이 발생한 경우에는 악취가 생기고 주변 장기인 직장이나 방광 요관 골반벽 좌골신경 등을 침범하게 되면 혈 소변 직장 출혈 허리통증 하지의 동통 및 부종 체중감소 등  나타나기도 합니다.")
                        binding.contentTv4c.setText("진단/검사 - 자궁경부 세포검사, 질 확대경 검사, 조직 생검, 원추절제술, 환상투열요법, 인유두종 바이러스 검사")
                        binding.contentTv4b.setVisibility(View.VISIBLE)
                        binding.contentTv4.setOnClickListener {binding.linearLayout4.setVisibility(View.VISIBLE) }
                        binding.cancel4.setOnClickListener { binding.linearLayout4.setVisibility(View.GONE) }

                        binding.contentTv5.setText("5. 방광 자궁탈출증")
                        binding.contentTv5c.setVisibility(View.VISIBLE)
                        binding.contentTv5a.setText("자궁탈출증은 자궁이 정상 위치에서 아래쪽 또는 위쪽으로 이동하면서 자궁의 일부 혹은 전체가 딜을 통해 빠져나오는 것을 말합니다. \n자궁을 지지해 주는 인대의 접착부인 질상부의 지지가 좋지 않아 발생하고 골반 지지 구조물의 약화로 인해 자궁 이외에도 직장소장 방광 등이 질강 쪽으로 탈출할 수 있습니다")
                        binding.contentTv5b.setText("증상 - 탈출 정도와 골반 증상과의 연관성은 적지만 골반장기탈출증 환자는 배뇨 이상과 관련된 증상이 동반됩니다 요실금 요로가 좁아지거나 막혀서 소변이 나오지 않는 요폐색 증상 하루 8번 이상 자주 소변을 보는 빈뇨 등을 호소할 수도 있습니다 가장 흔한 증상으로는 질 밖으로 어떤 물질이 돌출되어 나오는 느낌과 압박감입니다. \n일반적으로는 누워있는 자세에서 증상이 완화되고 오후 시간에 오랫동안 서 있는 경우에는 시간이 지남에 따라 악화되는 경향을 보일 수 있습니다.")
                        binding.contentTv5c.setText("진단/검사 - 골반 내진을 통해 탈출된 장기와 결손 부위를 확인합니다. \n많은 탈출증 환자에게서 요도의 과운동성이 나타나기 때문에 이에 대한 검사도 함께 시행됩니다. \n또한 자궁탈출증 환자의 경우 요도가 꺾이면서 요실금 증상이 나타나지 않거나 소변을 못 보는 경우도 있으므로 요검사를 통해 감염 여부를 확인하고 배뇨 후 잔뇨량을 측정합니다.")
                        binding.contentTv5b.setVisibility(View.VISIBLE)
                        binding.contentTv5.setOnClickListener {binding.linearLayout5.setVisibility(View.VISIBLE) }
                        binding.cancel5.setOnClickListener { binding.linearLayout5.setVisibility(View.GONE) }

                        binding.contentTv6.setVisibility(View.GONE)
                        binding.contentTv7.setVisibility(View.GONE)
                        binding.contentTv8.setVisibility(View.GONE)
                        binding.contentTv9.setVisibility(View.GONE)
                        binding.contentTv10.setVisibility(View.GONE)
                        binding.titleTv2.setVisibility(View.GONE)
                        binding.linearLayout6.setVisibility(View.GONE)
                        binding.linearLayout7.setVisibility(View.GONE)
                        binding.linearLayout8.setVisibility(View.GONE)
                        binding.linearLayout9.setVisibility(View.GONE)
                        binding.linearLayout10.setVisibility(View.GONE)
                        binding.viewTv6.setVisibility(View.GONE)
                        binding.viewTv7.setVisibility(View.GONE)
                        binding.viewTv8.setVisibility(View.GONE)
                        binding.viewTv9.setVisibility(View.GONE)
                        binding.viewTv10.setVisibility(View.GONE)
                        binding.viewTv5.setVisibility(View.VISIBLE)
                        binding.viewTv4.setVisibility(View.GONE)

                        binding.ivItem5.setVisibility(View.GONE)
                        binding.ivItem4.setVisibility(View.GONE)
                        binding.ivItem1.setVisibility(View.GONE)
                        binding.ivItem2.setVisibility(View.GONE)
                        binding.ivItem3.setVisibility(View.VISIBLE)

                        binding.viewIv1.setVisibility(View.GONE)
                        binding.viewIv2.setVisibility(View.GONE)
                        binding.viewIv3.setVisibility(View.GONE)
                        binding.viewIv4.setVisibility(View.GONE)
                        binding.viewIv5.setVisibility(View.GONE)
                        binding.viewIv6.setVisibility(View.GONE)
                        binding.viewIv7.setVisibility(View.GONE)
                        binding.viewIv8.setVisibility(View.GONE)
                    }
                    3 -> {binding.titleTv1.setText("산부인과 검사")


                        binding.contentTv1.setText("1. 복부/질/항문 초음파")
                        binding.contentTv1c.setVisibility(View.VISIBLE)
                        binding.contentTv1a.setText("- 여성질환이 의심 혹은 최초 발견인 경우\n" +
                                "- 여성질환 시술 및 수술 후 상태를 확인하는 경우\n" +
                                "- 임신으로 인해 초음파를 보는 경우\n")
                        binding.contentTv1b.setText("*4 ~ 13만 원대 ⇒ 무료 ~ 5만 원대*")
                        binding.contentTv1c.setText("생식기 기능에 이상이 있거나 임신 징후가 보여 산부인과를 방문하면, 초음파 검사를 가장 먼저 진행합니다. \n자궁과 난소의 상태를 확인할 수 있는 기본 검사이기 때문입니다.\n" +
                                "이 검사는 자궁이나 난소의 혹, 다낭성 난소 증후군처럼 질환적인 문제가 있을 때 절반의 비용만 지불하고 검사를 받을 수 있습니다. \n1년에 1번 의료 보험이 적용됩니다. \n또, 아기집이 확인된 경우에는 임신 주수에 따라 국가에서 검사를 지원해 줘서 무료로 검사를 받을 수도 있습니다. \n")
                        binding.contentTv1b.setVisibility(View.VISIBLE)
                        binding.contentTv1.setOnClickListener {binding.linearLayout1.setVisibility(View.VISIBLE) }
                        binding.cancel1.setOnClickListener { binding.linearLayout1.setVisibility(View.GONE) }

                        binding.contentTv2.setText("2. 유방 초음파")
                        binding.contentTv2c.setVisibility(View.VISIBLE)
                        binding.contentTv2a.setText("- 유방 질환이 의심되는 경우\n" +
                                "- 유방암 등 유방 질환의 상태를 관찰하는 경우\n")
                        binding.contentTv2b.setText("*7 ~ 14만 원대 ⇒ 3 ~ 6만 원대*")
                        binding.contentTv2c.setText("유방 초음파는 유방 엑스레이로 질환을 관찰하기 어려운 '치밀 유방'을 가진 여성들에게 꼭 필요한 검사입니다. \n특히 우리나라 여성들 중에는 치밀 유방을 가진 여성들이 많아 필수로 진행되는 유방 검사 중 하나입니다.\n" +
                                "유방 초음파는 유방암을 초기에 발견하고 치료하는데 큰 도움이 되는데 유방에 통증, 멍울과 같은 증상이 있거나 이전에 시행한 검사에서 이상이 발견되어 지속적으로 관찰이 필요한 경우에 보험 적용을 받을 수 있습니다.\n" +
                                "(대부분 초음파 검사는 의료보험, 건강보험 적용이 안되기 때문에 실비보험 적용만 가능하다.)\n")
                        binding.contentTv2b.setVisibility(View.VISIBLE)
                        binding.contentTv2.setOnClickListener {binding.linearLayout2.setVisibility(View.VISIBLE) }
                        binding.cancel2.setOnClickListener { binding.linearLayout2.setVisibility(View.GONE) }

                        binding.contentTv3.setText("3. 자궁경부암 검사(도말세포검사)")
                        binding.contentTv3c.setVisibility(View.VISIBLE)
                        binding.contentTv3a.setText("- 국가건강검진으로 받는 경우")
                        binding.contentTv3b.setText("*1 ~ 2만 원대 ⇒ 무료*")
                        binding.contentTv3c.setText("건강보험심사평가원에 따르면, 2020년도 2-30대 자궁경부암 환자 수가 4년 전에 비해 22.1% 늘었다고 합니다. \n그만큼 젊은 자궁경부암 환자가 많아지고 있는 것을 확인할 수 있습니다.\n" +
                                "국가에서는 자궁경부암 국가 검진을 지원하고 있습니다. 만 20세 이상의 여성이라면 2년마다 무료로 검사를 받을 수 있습니다. \n또, 국가 검진에서 이상이 발견되어 추가적으로 액상세포 검사를 해야 할 때도 혜택을 받을 수 있습니다.\n")
                        binding.contentTv3b.setVisibility(View.VISIBLE)
                        binding.contentTv3.setOnClickListener {binding.linearLayout3.setVisibility(View.VISIBLE) }
                        binding.cancel3.setOnClickListener { binding.linearLayout3.setVisibility(View.GONE) }

                        binding.contentTv4.setText("4. STD 검사")
                        binding.contentTv4c.setVisibility(View.VISIBLE)
                        binding.contentTv4a.setText("- 질염/성병 증상이 보이는 경우")
                        binding.contentTv4b.setText("*8만 원대 ⇒ 2 ~ 3만 원 대*")
                        binding.contentTv4c.setText("흔히 성병검사로 불리는 STD 검사는 헤르페스, 매독, 임질 등 다양한 성병을 검사할 수 있는 검사입니다. \n그뿐만 아니라 질염균도 검사할 수 있어서 질염이 자주 생기는 여성에게도 도움이 됩니다.\n" +
                                "이 검사는 생식기 가려움, 통증을 비롯한 질염 증상이나 성병 증상이 보이는 경우에 보험 비용 처리를 받아 저렴하게 검사를 받을 수 있습니다.\n" +
                                "비용 뿐만 아니라 사회 및 다른 사람들의 부정적인 시선 등 다른 이유로 산부인과를 선뜻 방문하지 못했던 여성들도 있습니다.\n" +
                                "산부인과 질환에 대한 사회적 관심이 확대되어 진료비 지원의 범위가 넓어져 온 것처럼 산부인과 방문을 어렵게 만드는 원인들이 사라져서 모든 여성들이 당당하게 스스로의 건강을 챙길 수 있는 날이 오길 바랍니다.\n")
                        binding.contentTv4b.setVisibility(View.VISIBLE)
                        binding.contentTv4.setOnClickListener {binding.linearLayout4.setVisibility(View.VISIBLE) }
                        binding.cancel4.setOnClickListener { binding.linearLayout4.setVisibility(View.GONE) }

                        binding.titleTv2.setText("산부인과 가기 전 필수 항목")
                        binding.titleTv2.setVisibility(View.VISIBLE)

                        binding.contentTv5.setText("1. 최근 월경 시작일과 종료일")
                        binding.contentTv5c.setVisibility(View.VISIBLE)
                        binding.contentTv5a.setText("산부인과에서 진료 접수를 하면 접수처에서 가장 최근에 한 월경의 시작일과 종료일을 적어야 합니다. \n(병원마다 차이가 있을 수는 있습니다.)")
                        binding.contentTv5b.setText("최근 월경 주기를 기준으로 검사 방법이 정해지기도 하고 의사 선생님께서 진료하실 때 중요한 근거가 되기 때문에 꼭 알고 가야 할 필수 정보입니다.")
                        binding.contentTv5c.setVisibility(View.GONE)
                        binding.contentTv5b.setVisibility(View.VISIBLE)
                        binding.contentTv5.setOnClickListener {binding.linearLayout5.setVisibility(View.VISIBLE) }
                        binding.cancel5.setOnClickListener { binding.linearLayout5.setVisibility(View.GONE) }

                        binding.contentTv6.setText("2. 먹고 있는 약, 알레르기가 있는 약 ")
                        binding.contentTv6c.setVisibility(View.VISIBLE)
                        binding.contentTv6a.setText("진료 후에 약을 처방받게 되는 경우가 있으니 현재 복용하고 있는 약이나 혹은 약물 알레르기가 있다면 알레르기가 있는 약의 종류를 메모해가야 합니다.")
                        binding.contentTv6b.setText("내 몸과 맞지 않거나 함께 복용해서는 안 되는 약을 피하려면 꼭 준비해 가야 할 항목입니다.")
                        binding.contentTv6c.setVisibility(View.GONE)
                        binding.contentTv6b.setVisibility(View.VISIBLE)
                        binding.contentTv6.setOnClickListener {binding.linearLayout6.setVisibility(View.VISIBLE) }
                        binding.cancel6.setOnClickListener { binding.linearLayout6.setVisibility(View.GONE) }

                        binding.contentTv7.setText("3. 팬티라이너 or 생리대")
                        binding.contentTv7c.setVisibility(View.VISIBLE)
                        binding.contentTv7a.setText("질 혹은 항문 초음파 검사를 받을 가능성이 있다면 팬티라이너나 생리대를 챙겨야 합니다. \n초음파 검사 기구를 넣을 때 사용되는 젤 형태의 윤활제가 시간이 지나 밖으로 흘러나오면서 속옷에 묻을 수 있기 때문이죠.")
                        binding.contentTv7b.setText("병원에서 팬티라이너를 비치해 두는 경우가 많지만 병원에서 따로 제공해 주지 않는 경우도 있기에 미리 준비해주시는게 좋습니다.")
                        binding.contentTv7c.setVisibility(View.GONE)
                        binding.contentTv7b.setVisibility(View.VISIBLE)
                        binding.contentTv7.setOnClickListener {binding.linearLayout7.setVisibility(View.VISIBLE) }
                        binding.cancel7.setOnClickListener { binding.linearLayout7.setVisibility(View.GONE) }

                        binding.contentTv8.setText("4. 갈아입기 쉬운 옷과 신발")
                        binding.contentTv8c.setVisibility(View.VISIBLE)
                        binding.contentTv8a.setText("질・항문 초음파 검사를 위해서는 속옷을 벗고 검사용 긴 치마를 입어야 합니다. \n갈아입기 쉬운 옷과 신발을 착용해야 검사가 수월해집니다")
                        binding.contentTv8b.setText("또, 검사 전에 신발을 슬리퍼로 갈아 신어야 하는 경우가 많기 때문에 롱부츠와 같이 신고 벗기가 불편한 종류의 신발들은 피하는 것을 추천합니다.")
                        binding.contentTv8c.setVisibility(View.GONE)
                        binding.contentTv8b.setVisibility(View.VISIBLE)
                        binding.contentTv8.setOnClickListener {binding.linearLayout8.setVisibility(View.VISIBLE) }
                        binding.cancel8.setOnClickListener { binding.linearLayout8.setVisibility(View.GONE) }

                        binding.contentTv9.setText("5. 가벼운 샤워")
                        binding.contentTv9c.setVisibility(View.VISIBLE)
                        binding.contentTv9a.setText("외음부가 청결할 때 생식기와 분비물의 상태를 더 쉽게 확인할 수 있기 때문에 산부인과에 방문하기 전에는 외음부를 씻고 가는 것이 좋습니다.")
                        binding.contentTv9b.setText("하지만 일부 질환의 경우 정확한 진단을 위해 검사 이틀 전부터 질 내부 세척은 피해야 합니다. \n가까운 시일 내에 산부인과 방문 계획이 있다면 질 세정제 사용은 멀리하고 샤워만 가볍게 해야 합니다.")
                        binding.contentTv9c.setVisibility(View.GONE)
                        binding.contentTv9b.setVisibility(View.VISIBLE)
                        binding.contentTv9.setOnClickListener {binding.linearLayout9.setVisibility(View.VISIBLE) }
                        binding.cancel9.setOnClickListener { binding.linearLayout9.setVisibility(View.GONE) }

                        binding.contentTv10.setText("6. 긍정적인 마음가짐")
                        binding.contentTv10c.setVisibility(View.VISIBLE)
                        binding.contentTv10a.setText("산부인과의 진찰대를 흔히 '굴욕 의자'라고 부르며 진료를 두려워하는 여성들이 많습니다. \n하지만 산부인과 진료는 단지 내 몸 상태를 확인하는 검사 과정일 뿐 입니다.")
                        binding.contentTv10b.setText("목감기에 걸리면 병원에서 목구멍을 살펴보는 것과 같이 산부인과에서는 생식기를 검사하는 것입니다. \n생식기관도 내 몸의 다른 장기나 기관과 같은 자연스러운 신체의 일부이기 때문에 진료 과정을 굴욕적으로 느낄 필요가 없답니다.")
                        binding.contentTv10c.setText("나의 건강을 위해 산부인과에 방문하는 것이니까 검사에 대한 부담감은 내려두고 긍정적인 마음을 가지고 방문해 보도록 합시다.")
                        binding.contentTv10b.setVisibility(View.VISIBLE)
                        binding.contentTv10.setOnClickListener {binding.linearLayout10.setVisibility(View.VISIBLE) }
                        binding.cancel10.setOnClickListener { binding.linearLayout10.setVisibility(View.GONE) }

                        binding.contentTv5.setVisibility(View.VISIBLE)
                        binding.contentTv6.setVisibility(View.VISIBLE)
                        binding.contentTv7.setVisibility(View.VISIBLE)
                        binding.contentTv8.setVisibility(View.VISIBLE)
                        binding.contentTv9.setVisibility(View.VISIBLE)
                        binding.contentTv10.setVisibility(View.VISIBLE)
                        binding.viewTv4.setVisibility(View.VISIBLE)
                        binding.viewTv5.setVisibility(View.VISIBLE)
                        binding.viewTv6.setVisibility(View.VISIBLE)
                        binding.viewTv7.setVisibility(View.VISIBLE)
                        binding.viewTv8.setVisibility(View.VISIBLE)
                        binding.viewTv9.setVisibility(View.VISIBLE)
                        binding.viewTv10.setVisibility(View.VISIBLE)
                        binding.ivItem2.setVisibility(View.VISIBLE)

                        binding.ivItem5.setVisibility(View.VISIBLE)
                        binding.ivItem4.setVisibility(View.VISIBLE)
                        binding.ivItem3.setVisibility(View.GONE)
                        binding.ivItem2.setVisibility(View.GONE)
                        binding.ivItem1.setVisibility(View.GONE)

                        binding.viewIv1.setVisibility(View.GONE)
                        binding.viewIv2.setVisibility(View.GONE)
                        binding.viewIv3.setVisibility(View.GONE)
                        binding.viewIv4.setVisibility(View.GONE)
                        binding.viewIv5.setVisibility(View.GONE)
                        binding.viewIv6.setVisibility(View.GONE)
                        binding.viewIv7.setVisibility(View.GONE)
                        binding.viewIv8.setVisibility(View.GONE)
                    }

                }
            }
        }


    }
}
