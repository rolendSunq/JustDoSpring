package com.justdospring.myspring.ch02.part12.junit_studytest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class JUnitTest {
	static JUnitTest testObject;
	
	@Test
	public void test1() {
		assertThat(this, equalTo(not(sameInstance(testObject))));
		testObject = this;
	}
	
	@Test
	public void test2() {
		assertThat(this, equalTo(not(sameInstance(testObject))));
		testObject = this;
	}
	
	@Test
	public void test3() {
		assertThat(this, equalTo(not(sameInstance(testObject))));
	}
}
