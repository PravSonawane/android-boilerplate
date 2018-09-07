package ui.bindings

import android.databinding.BindingAdapter
import android.widget.DatePicker



/**
 * Binding adapters for [DatePicker]
 */
class DatePickerBindings {

  companion object {

    @JvmStatic
    @BindingAdapter("cDatePickerYear", "cDatePickerMonth", "cDatePickerDay", "cDatePickerOnDateChanged")
    fun setDate(view: DatePicker, year: Int, month: Int, day: Int,
        listener: DatePicker.OnDateChangedListener) {
      view.init(year, month, day, listener)
    }

    @JvmStatic
    @BindingAdapter("cDatePickerYear", "cDatePickerMonth", "cDatePickerDay")
    fun setDate(view: DatePicker, year: Int, month: Int, day: Int) {
      view.updateDate(year, month, day)
    }

    @JvmStatic
    @BindingAdapter("cDatePickerYear", "cDatePickerMonth")
    fun setYearMonth(view: DatePicker, year: Int, month: Int) {
      setDate(view, year, month, view.dayOfMonth)
    }

    @JvmStatic
    @BindingAdapter("cDatePickerMonth", "cDatePickerDay")
    fun setMonthDay(view: DatePicker, month: Int, day: Int) {
      setDate(view, view.year, month, day)
    }

    @JvmStatic
    @BindingAdapter(value = ["cDatePickerYear", "cDatePickerDay", "cDatePickerOnDateChanged"],
        requireAll = false)
    fun setYearDay(view: DatePicker, year: Int, day: Int,
        listener: DatePicker.OnDateChangedListener) {
      setDate(view, year, view.month, day, listener)
    }

    @JvmStatic
    @BindingAdapter("cDatePickerYear", "cDatePickerMonth", "cDatePickerOnDateChanged")
    fun setYearMonth(view: DatePicker, year: Int, month: Int,
        listener: DatePicker.OnDateChangedListener) {
      setDate(view, year, month, view.dayOfMonth, listener)
    }

    @JvmStatic
    @BindingAdapter("cDatePickerMonth", "cDatePickerDay", "cDatePickerOnDateChanged")
    fun setMonthDay(view: DatePicker, month: Int, day: Int,
        listener: DatePicker.OnDateChangedListener) {
      setDate(view, view.year, month, day, listener)
    }

  }
}