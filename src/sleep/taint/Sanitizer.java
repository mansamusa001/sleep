/* 
 * Copyright (C) 2002-2012 Raphael Mudge (rsmudge@gmail.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package sleep.taint;

import sleep.engine.*;
import sleep.interfaces.*;
import sleep.runtime.*;

import java.util.*;

public class Sanitizer implements Function, Operator
{
   protected Object function;

   public Sanitizer(Object f)
   {
      function = f;
   }

   public Scalar operate(String name, ScriptInstance script, Stack arguments)
   {
      Scalar value = ((Operator)function).operate(name, script, arguments);
      TaintUtils.untaint(value);
      return value;
   }

   public Scalar evaluate(String name, ScriptInstance script, Stack arguments)
   {
      Scalar value = ((Function)function).evaluate(name, script, arguments);
      TaintUtils.untaint(value);
      return value;
   }
}
