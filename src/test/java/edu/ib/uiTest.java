//Copyright (C) 2020, Grzegorz Stefański
package edu.ib;

import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TextInputControlMatchers;

class uiTest extends ApplicationTest {

  public void testInput() {
    clickOn("#input").write("1");
    FxAssert.verifyThat("#input", TextInputControlMatchers.hasText("1"));
  }

  public void testInput2() {
    write("1");
    FxAssert.verifyThat("#input", TextInputControlMatchers.hasText("11"));
  }
}
