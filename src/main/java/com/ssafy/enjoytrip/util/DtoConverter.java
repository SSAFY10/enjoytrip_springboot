package com.ssafy.enjoytrip.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;


public class DtoConverter<T , R> {
	
	public R convert(T target, R result) {
		
		List<Method> getMethodList = Arrays.stream(target.getClass().getDeclaredMethods())
				.filter(method -> method.getName().substring(0, 3).equals("get"))
				.collect(Collectors.toList());
		getMethodList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		
		Queue<Method> getMethodQueue = new ArrayDeque<>(getMethodList);
		
		
		List<Method> setMethodList = Arrays.stream(result.getClass().getDeclaredMethods())
				.filter(method -> method.getName().substring(0, 3).equals("set"))
				.collect(Collectors.toList());
		setMethodList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		
		Queue<Method> setMethodQueue = new ArrayDeque<>(setMethodList);
		
		Method get;
		Method set;
		if (setMethodQueue.size() > getMethodQueue.size()) {
			while (!setMethodQueue.isEmpty() && !getMethodQueue.isEmpty()) {
				set = setMethodQueue.poll();
				if ((get = getMethodQueue.peek()) != null) {
					String setName = set.getName();
					String getName = get.getName();
					if (setName.substring(3, setName.length()).equals(getName.substring(3, getName.length()))) {
						try {
							switch (set.getParameterTypes()[0].toString()) {
							case "int":
								set.invoke(result, (Integer) get.invoke(target, null));
								break;
							case "class java.lang.String":
								set.invoke(result, String.valueOf(get.invoke(target, null)));
								break;
							case "double":
								set.invoke(result, (double) get.invoke(target, null));
								break;
							}
							getMethodQueue.poll();
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
					else {
						setMethodQueue.add(set);
					}
				}
			}
		}
		else {
			while (!setMethodQueue.isEmpty() && !getMethodQueue.isEmpty()) {
				get = getMethodQueue.poll();
				if ((set = setMethodQueue.peek()) != null) {
					String setName = set.getName();
					String getName = get.getName();
					if (setName.substring(3, setName.length()).equals(getName.substring(3, getName.length()))) {
						try {
							switch (set.getParameterTypes()[0].toString()) {
							case "int":
								set.invoke(result, (int) get.invoke(target, null));
								break;
							case "class java.lang.String":
								set.invoke(result, String.valueOf(get.invoke(target, null)));
								break;
							case "double":
								set.invoke(result, (double) get.invoke(target, null));
								break;
							}
							setMethodQueue.poll();
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
					else {
						getMethodQueue.add(get);
					}
				}
			}
		}
		
		return result;

	}

}
