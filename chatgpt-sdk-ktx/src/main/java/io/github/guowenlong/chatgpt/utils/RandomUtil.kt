package io.github.guowenlong.chatgpt.utils

/**
 * Description: 随机数工具
 * Author:      郭文龙
 * Date:        2023/4/3 23:08
 * Email:       guowenlong20000@sina.com
 */
object RandomUtil {
    /**
     * 从0到max之间的随机整数
     */
    fun randomInt(min: Int, max: Int): Int {
        return (min..max).random()
    }
}