package org.zhscript.clas

import java.lang.UnsupportedOperationException

class Buzhichi_(var s:String?) : UnsupportedOperationException(
        if (s == null) s_
        else if (s.startsWith("-")) s_ + s.substring(1)
        else s
) {
    companion object {
        var s_ = "不支持"
    }
}