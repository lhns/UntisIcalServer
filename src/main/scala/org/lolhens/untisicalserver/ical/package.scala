package org.lolhens.untisicalserver

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.function.Predicate

import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.Component
import net.fortuna.ical4j.model.ComponentList
import net.fortuna.ical4j.model.Property
import net.fortuna.ical4j.model.component.CalendarComponent

import scala.collection.JavaConversions._


/**
  * Created by pierr on 01.09.2016.
  */
package object ical {
  def ComponentList[E <: Component](components: List[E]) = {
    val componentList = new ComponentList[E]()
    componentList.addAll(components)
    componentList
  }

  implicit val localDateTimeOrdering: Ordering[LocalDateTime] = Ordering.fromLessThan(_ isBefore _)

  implicit class RichDate(val date: Date) extends AnyVal {
    def toLocalDateTime = LocalDateTime.ofInstant(date.toInstant, ZoneId.systemDefault)
  }

  implicit class RichCalendar(val calendar: Calendar) extends AnyVal {
    def setComponents(components: List[CalendarComponent]): Unit = {
      calendar.getComponents.clear()
      calendar.getComponents.addAll(components)
    }
  }

  implicit class RichCalendarComponent(val component: CalendarComponent) extends AnyVal {
    def removeProperty(name: String) =
      component.getProperties().removeIf(new Predicate[Property]() {
        override def test(t: Property): Boolean = t.getName.equalsIgnoreCase(name)
      })

    def addProperty(property: Property) =
      component.getProperties.add(property)
  }

}
