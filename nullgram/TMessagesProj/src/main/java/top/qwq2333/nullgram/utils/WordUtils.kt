package top.qwq2333.nullgram.utils


object WordUtils {


    private val nounMap: MutableMap<String, String> = mutableMapOf("你" to "我", "您" to "咱", "他" to "怹", "她" to "怹", "它" to "怹")
    private val verbMap: MutableMap<String, String> = mutableMapOf("要不是" to "要是", "不是" to "是", "没有" to "有", "不要" to "要")
    private fun generateBimap(map: MutableMap<String, String>): MutableMap<String, String> {
        val keys = map.keys.toMutableList()
        val values = map.values.toMutableList()
        val result = mutableMapOf<String, String>()
        for (i in keys.indices) {
            result[values[i]] = keys[i]
        }
        return result
    }

    init {
        nounMap.putAll(generateBimap(nounMap))
//        verbMap.putAll(generateBimap(verbMap))
    }

    fun replaceAntonyms(string: String?): String {
        if (string == null) return ""
        var result = string
        // 因为是双向映射，所以要避免被替换两次
        nounMap.onEachIndexed { index, entry ->
            result = result?.split(entry.key)?.joinToString("_noun_$index")
        }
//        verbMap.onEachIndexed { index, entry ->
//            result = result?.split(entry.key)?.joinToString("_verb_$index")
//        }
        nounMap.onEachIndexed { index, entry ->
            result = result?.split("_noun_$index")?.joinToString(entry.value)
        }
//        verbMap.onEachIndexed { index, entry ->
//            result = result?.split("_verb_$index")?.joinToString(entry.value)
//        }

        return result.toString()
    }
}
