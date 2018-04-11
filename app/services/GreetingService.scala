package services

import java.util.Calendar

import com.google.inject.ImplementedBy

@ImplementedBy(classOf[RealGreetingService])
trait GreetingService {
  def greeting: String
}

class RealGreetingService extends GreetingService {
  def greeting: String = {
    val now = Calendar.getInstance()
    val currentHour = now.get(Calendar.HOUR_OF_DAY)
    if(currentHour < 12)
      "Good Morning"
    else
      "Good Afternoon"
  }
}
