package com.app.localdatabase.listener

interface OnItemClickListener<T> {
    fun onClickNotify(model: T, pos: Int)
}