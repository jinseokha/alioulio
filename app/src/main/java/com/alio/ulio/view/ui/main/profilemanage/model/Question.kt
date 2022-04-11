package com.alio.ulio.view.ui.main.profilemanage.model

internal object Question {
    val data : HashMap<String, List<String>>
    get() {
        val expandableListDetail = HashMap<String, List<String>>()

        val answer : MutableList<String> = ArrayList()
        answer.add("청춘에게만 인도하겠다는 없으면 ...  청춘에게만 인도하겠다는 없으면 ... 청춘에게만 인도하겠다는 없으면 ... 청춘에게만 인도하겠다는 없으면 ..." +
                "청춘에게만 인도하겠다는 없으면 ... 청춘에게만 인도하겠다는 없으면 ...청춘에게만 인도하겠다는 없으면 ... 청춘에게만 인도하겠다는 없으면 ... 청춘에게만 인도하겠다는 없으면 ... " +
                "청춘에게만 인도하겠다는 없으면 ..." +
                "청춘에게만 인도하겠다는 없으면 ...청춘에게만 인도하겠다는 없으면 ...청춘에게만 인도하겠다는 없으면 ...")

        expandableListDetail["Q. 알람은 어떻게 보낼 수 있나요?"] = answer
        expandableListDetail["Q. 알람은 어"] = answer
        expandableListDetail["Q. 알람은 어떻게요?"] = answer
        expandableListDetail["Q. 알람은 어떻게 보나요?"] = answer
        expandableListDetail["Q. 알람은 어떻게 요?"] = answer
        expandableListDetail["Q. 알람은 어떻게요?"] = answer

        return expandableListDetail
    }

}