package com.sevketbuyukdemir.ieltsprepapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sevketbuyukdemir.ieltsprepapp.Fragments.ReadingQuestionFragment
import com.sevketbuyukdemir.ieltsprepapp.Fragments.ReadingTextFragment
import com.sevketbuyukdemir.ieltsprepapp.utils.Question
import com.sevketbuyukdemir.ieltsprepapp.view_adapters.ReadingActivityViewPagerAdapter

class ReadingActivity : AppCompatActivity() {
    val context : Context = this
    /**
     * We can still use [@JvmStatic] for 'var' variables, but it generates getter/setters as
     * functions of KotlinClass. If we use 'val' instead, it only generates a getter function
     */
    companion object {
        private var instance: ReadingActivity? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        @JvmStatic
        lateinit var firstSectionAnswers: Array<String>

        @JvmStatic
        lateinit var secondSectionAnswers: Array<String>

        @JvmStatic
        lateinit var thirdSectionAnswers: Array<String>


        // Answers arraylists this arraylist include right answers
        private val answersFirstSection = ArrayList<String>()
        private val answersSecondSection = ArrayList<String>()
        private val answersThirdSection = ArrayList<String>()

        @JvmStatic
        var submittedSectionCount: Int = 0

        @JvmStatic
        fun submitAllListeningSections() {
            var gradeListening = 0
            if (submittedSectionCount == 3) {
                var counter: Int = 0
                while (counter < 13) {
                    if (firstSectionAnswers[counter] == answersFirstSection[counter]) {
                        gradeListening++
                    }
                    counter++
                }
                counter = 0
                while (counter < 13) {
                    if (secondSectionAnswers[counter] == answersSecondSection[counter]) {
                        gradeListening++
                    }
                    counter++
                }
                counter = 0
                while (counter < 14) {
                    if (thirdSectionAnswers[counter] == answersThirdSection[counter]) {
                        gradeListening++
                    }
                    counter++
                }
                println("Your grade : $gradeListening")
                Toast.makeText(ReadingActivity.applicationContext(), "Your grade : $gradeListening", Toast.LENGTH_LONG).show()
                val intent = Intent(ReadingActivity.applicationContext(), MainActivity::class.java)
                ReadingActivity.applicationContext().startActivity(intent)
            }
        }
    }

    // View pager and Fragments
    private lateinit var readingActivityViewPagerAdapter: ReadingActivityViewPagerAdapter
    private lateinit var readingTextFragmentFirst: ReadingTextFragment
    private lateinit var readingTextFragmentSecond : ReadingTextFragment
    private lateinit var readingTextFragmentThird : ReadingTextFragment
    private lateinit var readingQuestionFragmentFirst : ReadingQuestionFragment
    private lateinit var readingQuestionFragmentSecond : ReadingQuestionFragment
    private lateinit var readingQuestionFragmentThird : ReadingQuestionFragment

    // Data sources for fragments
    private val readingTextFragmentFirstDataSource = ArrayList<String>()
    private val readingTextFragmentSecondDataSource = ArrayList<String>()
    private val readingTextFragmentThirdDataSource = ArrayList<String>()
    private val readingQuestionFragmentFirstQuestionList = ArrayList<Question>()
    private val readingQuestionFragmentSecondQuestionList = ArrayList<Question>()
    private val readingQuestionFragmentThirdQuestionList = ArrayList<Question>()

    // Global objects for UI elements
    private lateinit var readingTabLayout : TabLayout
    private lateinit var readingViewpager : ViewPager

    /**
     * Function for initialize UI elements and necessary adapters and data type
     */
    private fun init(){
        // Bind views with Ids
        readingTabLayout = findViewById(R.id.reading_tab_layout)
        readingViewpager = findViewById(R.id.reading_viewpager)
        fillReadingArrayLists()
        fillQuestionArrayLists()
        fillAnswersArrayList()
        initiateAnswerArrays()
        initiateFragments()
        setAndInitiateViewPagerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reading)
        init()
        ReadingActivity.instance = this
    }

    private fun fillAnswersArrayList(){
        // First reading section
        ReadingActivity.answersFirstSection.add("water table well")
        ReadingActivity.answersFirstSection.add("natural spring")
        ReadingActivity.answersFirstSection.add("confined aquifer")
        ReadingActivity.answersFirstSection.add("explosive fountain")
        ReadingActivity.answersFirstSection.add("extraction pump")
        ReadingActivity.answersFirstSection.add("chlorination")
        ReadingActivity.answersFirstSection.add("chlorination")
        ReadingActivity.answersFirstSection.add("reverse osmosis")
        ReadingActivity.answersFirstSection.add("UV light treatment")
        ReadingActivity.answersFirstSection.add("distillation")
        ReadingActivity.answersFirstSection.add("reverse osmosis")
        ReadingActivity.answersFirstSection.add("Tap water may not have a nice flavour.")
        ReadingActivity.answersFirstSection.add("Tap water is usually safe to drink.")
        // Second reading section
        ReadingActivity.answersSecondSection.add("C")
        ReadingActivity.answersSecondSection.add("E")
        ReadingActivity.answersSecondSection.add("F")
        ReadingActivity.answersSecondSection.add("B")
        ReadingActivity.answersSecondSection.add("C")
        ReadingActivity.answersSecondSection.add("D")
        ReadingActivity.answersSecondSection.add("commercial and civic")
        ReadingActivity.answersSecondSection.add("ornamentation")
        ReadingActivity.answersSecondSection.add("ribbon design")
        ReadingActivity.answersSecondSection.add("prestige")
        ReadingActivity.answersSecondSection.add("function practicality")
        ReadingActivity.answersSecondSection.add("D")
        ReadingActivity.answersSecondSection.add("B")
        // Third reading section
        ReadingActivity.answersThirdSection.add("P")
        ReadingActivity.answersThirdSection.add("H")
        ReadingActivity.answersThirdSection.add("F")
        ReadingActivity.answersThirdSection.add("G")
        ReadingActivity.answersThirdSection.add("N")
        ReadingActivity.answersThirdSection.add("J")
        ReadingActivity.answersThirdSection.add("C")
        ReadingActivity.answersThirdSection.add("TRUE")
        ReadingActivity.answersThirdSection.add("FALSE")
        ReadingActivity.answersThirdSection.add("FALSE")
        ReadingActivity.answersThirdSection.add("NOT GIVEN")
        ReadingActivity.answersThirdSection.add("TRUE")
        ReadingActivity.answersThirdSection.add("FALSE")
        ReadingActivity.answersThirdSection.add("D) warmer water can freeze faster than colder water.")
    }

    private fun initiateAnswerArrays(){
        ReadingActivity.firstSectionAnswers = Array(readingQuestionFragmentFirstQuestionList.size) { "null" }
        ReadingActivity.secondSectionAnswers = Array(readingQuestionFragmentSecondQuestionList.size) { "null" }
        ReadingActivity.thirdSectionAnswers = Array(readingQuestionFragmentThirdQuestionList.size) { "null" }
    }

    private fun fillReadingArrayLists(){
        // First Data Source
        readingTextFragmentFirstDataSource.add(getString(R.string.reading_first_warning))
        readingTextFragmentFirstDataSource.add(getString(R.string.reading_first_title))
        readingTextFragmentFirstDataSource.add(getString(R.string.reading_first_paragraph_first))
        readingTextFragmentFirstDataSource.add(getString(R.string.reading_first_paragraph_second))
        readingTextFragmentFirstDataSource.add(getString(R.string.reading_first_paragraph_third))
        readingTextFragmentFirstDataSource.add(getString(R.string.reading_first_paragraph_forth))
        readingTextFragmentFirstDataSource.add(getString(R.string.reading_first_paragraph_fifth))
        readingTextFragmentFirstDataSource.add(getString(R.string.reading_first_paragraph_sixth))
        readingTextFragmentFirstDataSource.add(getString(R.string.reading_first_paragraph_seventh))
        readingTextFragmentFirstDataSource.add(getString(R.string.reading_first_paragraph_eight))

        // Second Data Source
        readingTextFragmentSecondDataSource.add(getString(R.string.reading_second_warning))
        readingTextFragmentSecondDataSource.add(getString(R.string.reading_second_title))
        readingTextFragmentSecondDataSource.add(getString(R.string.reading_second_paragraph_first))
        readingTextFragmentSecondDataSource.add(getString(R.string.reading_second_paragraph_second))
        readingTextFragmentSecondDataSource.add(getString(R.string.reading_second_paragraph_third))
        readingTextFragmentSecondDataSource.add(getString(R.string.reading_second_paragraph_forth))
        readingTextFragmentSecondDataSource.add(getString(R.string.reading_second_paragraph_fifth))
        readingTextFragmentSecondDataSource.add(getString(R.string.reading_second_paragraph_sixth))

        // Third Data Source
        readingTextFragmentThirdDataSource.add(getString(R.string.reading_third_warning))
        readingTextFragmentThirdDataSource.add(getString(R.string.reading_third_title))
        readingTextFragmentThirdDataSource.add(getString(R.string.reading_third_paragraph_first))
        readingTextFragmentThirdDataSource.add(getString(R.string.reading_third_paragraph_second))
        readingTextFragmentThirdDataSource.add(getString(R.string.reading_third_paragraph_third))
        readingTextFragmentThirdDataSource.add(getString(R.string.reading_third_paragraph_forth))
        readingTextFragmentThirdDataSource.add(getString(R.string.reading_third_paragraph_fifth))
        readingTextFragmentThirdDataSource.add(getString(R.string.reading_third_paragraph_sixth))
        readingTextFragmentThirdDataSource.add(getString(R.string.reading_third_paragraph_seventh))
        readingTextFragmentThirdDataSource.add(getString(R.string.reading_third_paragraph_eight))
    }

    /**
     * '^' character is special character for put blank to fill in the blank question
     */
    private fun fillQuestionArrayLists(){
        // Fill first question list
        readingQuestionFragmentFirstQuestionList.add(
            Question(false,
            "1) ^ (provides access to trapped water)"))
        readingQuestionFragmentFirstQuestionList.add(
            Question(false,
                "2) ^ (due to the lower land level)"))
        readingQuestionFragmentFirstQuestionList.add(
            Question(false,
                "3) ^"))
        readingQuestionFragmentFirstQuestionList.add(
            Question(false,
                "4) flowing artesian well (looks like ^)"))
        readingQuestionFragmentFirstQuestionList.add(
            Question(false,
                "5) ^ is necessary to access this water source"))
        readingQuestionFragmentFirstQuestionList.add(
            Question(true,
                "6) It continues to protect water as it is being transported.",
                arrayOf("reverse osmosis",
                    "UV light treatment",
                    "chlorination",
                    "distillation")))
        readingQuestionFragmentFirstQuestionList.add(
            Question(true,
                "7) It is particularly useful during emergencies.",
                arrayOf("reverse osmosis",
                    "UV light treatment",
                    "chlorination",
                    "distillation")))
        readingQuestionFragmentFirstQuestionList.add(
            Question(true,
                "8) It uses a physical barrier to separate unwanted matter from water.",
                arrayOf("reverse osmosis",
                    "UV light treatment",
                    "chlorination",
                    "distillation")))
        readingQuestionFragmentFirstQuestionList.add(
            Question(true,
                "9) It prevents bacteria from reproducing.",
                arrayOf("reverse osmosis",
                    "UV light treatment",
                    "chlorination",
                    "distillation")))
        readingQuestionFragmentFirstQuestionList.add(
            Question(true,
                "10) It removes all mineral particles.",
                arrayOf("reverse osmosis",
                    "UV light treatment",
                    "chlorination",
                    "distillation")))
        readingQuestionFragmentFirstQuestionList.add(
            Question(true,
                "11) It produces a lot of waste water.",
                arrayOf("reverse osmosis",
                    "UV light treatment",
                    "chlorination",
                    "distillation")))
        readingQuestionFragmentFirstQuestionList.add(
            Question(true,
                "Questions 12 and 13\n" +
                        "Choose TWO letters, A–E.\n" +
                        "Write the correct letters in boxes 12 and 13 on your answer sheet.\n" +
                        "Which TWO of the following claims about water are made by the writer?\n " +
                        "for 12 :",
                arrayOf("Bottled water is overpriced.",
                    "Tap water may not have a nice flavour.",
                    "Most people should drink bottled water.",
                    "Tap water is usually safe to drink.",
                    "Public water supplies need better maintenance.")))
        readingQuestionFragmentFirstQuestionList.add(
            Question(true,
                "Questions 12 and 13\n" +
                        "Choose TWO letters, A–E.\n" +
                        "Write the correct letters in boxes 12 and 13 on your answer sheet.\n" +
                        "Which TWO of the following claims about water are made by the writer?\n " +
                        "for 13 :",
                arrayOf("Bottled water is overpriced.",
                    "Tap water may not have a nice flavour.",
                    "Most people should drink bottled water.",
                    "Tap water is usually safe to drink.",
                    "Public water supplies need better maintenance.")))

        // Fill second question list
        readingQuestionFragmentSecondQuestionList.add(
            Question(false,
                "Questions 14–19\n" +
                        "Reading Passage 1 has six paragraphs, A–F.\n" +
                        "Which paragraph contains the following information?\n" +
                        "Write the correct letter, A–F, in boxes 14–19 on your answer sheet.\n" +
                        "14) a description of how international style buildings look on the inside ^"))
        readingQuestionFragmentSecondQuestionList.add(
            Question(false,
                "15) a reference to institutions that didn’t like to use " +
                        "international style buildings ^"))
        readingQuestionFragmentSecondQuestionList.add(Question(false, "16) a reason why architects didn’t like the international style ^"))
        readingQuestionFragmentSecondQuestionList.add(Question(false, "17) a building which combined art deco and international features ^"))
        readingQuestionFragmentSecondQuestionList.add(Question(false, "18) types of materials commonly used in international style buildings ^"))
        readingQuestionFragmentSecondQuestionList.add(Question(false, "19) an architectural feature previously associated with prominent organisations ^"))
        readingQuestionFragmentSecondQuestionList.add(Question(false, "Questions 20–24\n" +
                "Complete the sentences below.\n" +
                "Choose NO MORE THAN THREE WORDS from the passage for each answer.\n" +
                "Write your answers in boxes 20–24 on your answer sheet.\n" +
                "20) The development of the international style was prompted by an increased need for\n" +
                "^"))
        readingQuestionFragmentSecondQuestionList.add(Question(false, "buildings\n" +
                "21) Designers used hardly any ^"))
        readingQuestionFragmentSecondQuestionList.add(Question(false, "on international style buildings.\n" +
                "22) International style buildings are easily identified from the outside because of the ^"))
        readingQuestionFragmentSecondQuestionList.add(Question(false, "23) Demonstration of ^"))
        readingQuestionFragmentSecondQuestionList.add(Question(false, "was often an important factor in the design of old-style buildings." +
                "\n24) The similarity of international style constructions reflected the concern of architects ^"))
        readingQuestionFragmentSecondQuestionList.add(Question(true,
            "Questions 25–26\n" +
                    "Choose the correct letter, A, B, C, or D.\n" +
                    "25) Some people did not like the international style because they felt it focused too much on",
            arrayOf("the public sector",
                "differences between people",
                "new ideas",
                "making money.")))
        readingQuestionFragmentSecondQuestionList.add(Question(true,
            "26) In the mid-1970s",
            arrayOf("the best architects were no longer using the international style.",
                "there was a lot of international style architecture in major cities.",
                "young architects were becoming interested in the international style.",
                "people visited cities specifically to see international style buildings.")))

        // Fill third question list
        readingQuestionFragmentThirdQuestionList.add(Question(false,
            "Questions 27–33\n" +
                    "Complete the summary using the list of words, A–O, below.\n" +
                    "Write the correct letter, A–O, in boxes 29–34 on your answer sheet.\n" +
                    "A melt\n B element\n C process\n" +
                    "D centre\n E acceleration\n F surface\n" +
                    "G factor\n H hollow\n I matter\n" +
                    "J circulation\n K limit\n L significance\n" +
                    "M theory\n N difference\n O result\n P temperature" +
                    "For more than 2000 years people have wondered why raising the\n 27) ^"))
        readingQuestionFragmentThirdQuestionList.add(Question(false,
            "of cold water before cooling it results\n" +
                    "in more rapid cooling. At first researchers thought that a warm " +
                    "container created its own icy\n 28) ^"))
        readingQuestionFragmentThirdQuestionList.add(Question(false,
            "which\n" +
                    "made the water freeze faster, but comparisons with containers resting on a dry\n" +
                    " 29) ^"))
        readingQuestionFragmentThirdQuestionList.add(Question(false,
            "indicated that this\n" +
                    "was inaccurate. Evaporation of water proved not to be a\n 30) ^"))
        readingQuestionFragmentThirdQuestionList.add(Question(false,
            "Temperature measure-\n" +
                    "ments showed that,\n" +
                    "although the water in the cooler container reached 00C before the warmer one, it took longer to actu-\n" +
                    "ally solidify. The water\n" +
                    "temperature drops the most at the top and sides of the container. Provided there is a temperature\n" +
                    "31) ^"))
        readingQuestionFragmentThirdQuestionList.add(Question(false,
            "the\n" +
                    "water will continue to circulate and to cool down. Cooler water will have less water " +
                    "\n 32) ^"))
        readingQuestionFragmentThirdQuestionList.add(Question(false,
            "and thus a slower\n" +
                    "rate of freezing. If ice forms on the top of the water, this will further slow the\n 33) ^" +
                    "of freezing, but if it forms\n" +
                    "on the bottom and the sides of the container,\n " +
                    "this will increase the rate of cooling."))
        readingQuestionFragmentThirdQuestionList.add(Question(true,
            "34) The Mpemba Effect cannot be seen when comparing liquids with an extreme temperature dif-\n" +
                    "ference .",
            arrayOf("TRUE",
                "FALSE",
                "NOT GIVEN")))
        readingQuestionFragmentThirdQuestionList.add(Question(true,
            "35) Osborne and Mpemba’s results are still widely accepted today.",
            arrayOf("TRUE",
                "FALSE",
                "NOT GIVEN")))
        readingQuestionFragmentThirdQuestionList.add(Question(true,
            "36) The size of the container does not alter the Mpemba Effect.",
            arrayOf("TRUE",
                "FALSE",
                "NOT GIVEN")))
        readingQuestionFragmentThirdQuestionList.add(Question(true,
            "37) Osborne and Mpemba experimented on both pure and impure water.",
            arrayOf("TRUE",
                "FALSE",
                "NOT GIVEN")))
        readingQuestionFragmentThirdQuestionList.add(Question(true,
            "38) One variable is the timing of containers in a freezer.",
            arrayOf("TRUE",
                "FALSE",
                "NOT GIVEN")))
        readingQuestionFragmentThirdQuestionList.add(Question(true,
            "39) Physicists now agree that supercooling accounts for the Mpemba Effect.",
            arrayOf("TRUE",
                "FALSE",
                "NOT GIVEN")))
        readingQuestionFragmentThirdQuestionList.add(Question(true,
            "Question 40)\n" +
                    "Choose the correct letter, A, B, C or D.\n" +
                    "Write the correct letter in box 40 on your answer sheet.\n" +
                    "The Mpemba Effect is best summed up as the observation that",
            arrayOf("A) ice cream freezes at different temperatures.",
                "B) different sources of heat result in water cooling at different rates.",
                "C) salt water freezes at a lower temperature than ordinary water.",
                "D) warmer water can freeze faster than colder water.")))
    }

    private fun initiateFragments(){
        readingTextFragmentFirst = ReadingTextFragment(1, readingTextFragmentFirstDataSource)
        readingTextFragmentSecond = ReadingTextFragment(2, readingTextFragmentSecondDataSource)
        readingTextFragmentThird = ReadingTextFragment(3, readingTextFragmentThirdDataSource)
        readingQuestionFragmentFirst = ReadingQuestionFragment(1, readingQuestionFragmentFirstQuestionList)
        readingQuestionFragmentSecond = ReadingQuestionFragment(2, readingQuestionFragmentSecondQuestionList)
        readingQuestionFragmentThird = ReadingQuestionFragment(3, readingQuestionFragmentThirdQuestionList)
    }

    private fun setAndInitiateViewPagerAdapter(){
        readingActivityViewPagerAdapter = ReadingActivityViewPagerAdapter(supportFragmentManager)
        readingActivityViewPagerAdapter.addFragment(readingTextFragmentFirst, "READING - 1")
        readingActivityViewPagerAdapter.addFragment(readingQuestionFragmentFirst, "QUESTION - 1")
        readingActivityViewPagerAdapter.addFragment(readingTextFragmentSecond, "READING - 2")
        readingActivityViewPagerAdapter.addFragment(readingQuestionFragmentSecond, "QUESTION - 2")
        readingActivityViewPagerAdapter.addFragment(readingTextFragmentThird, "READING - 3")
        readingActivityViewPagerAdapter.addFragment(readingQuestionFragmentThird, "QUESTION - 3")
        readingViewpager.adapter = readingActivityViewPagerAdapter
        readingTabLayout.setupWithViewPager(readingViewpager)
    }
}