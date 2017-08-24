package org.lolhens.untisicalserver.ical

import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

import org.lolhens.untisicalserver.ical.WeekOfYear._

/**
  * Created by pierr on 24.03.2017.
  */
case class WeekOfYear(year: Int, week: Int) {
  lazy val localDate: LocalDate = LocalDate.now()
    .withYear(year)
    .`with`(weekFields.weekOfYear(), 1)
    .`with`(weekFields.dayOfWeek(), 1)
    .plusWeeks(week - 1)

  def +(weeks: Int): WeekOfYear = WeekOfYear(localDate.plusWeeks(weeks))

  def -(weeks: Int): WeekOfYear = this + -weeks
}

object WeekOfYear {
  private val weekFields = WeekFields.of(Locale.getDefault())

  def apply(localDate: LocalDate): WeekOfYear =
    WeekOfYear(localDate.getYear, localDate.get(weekFields.weekOfYear()))

  def apply(year: Int, weekRange: Range): WeekRange = WeekRange(
    WeekOfYear(year, weekRange.start),
    WeekOfYear(year, if (weekRange.isInclusive) weekRange.end else weekRange.end - 1)
  )

  def now: WeekOfYear = WeekOfYear(LocalDate.now())
}