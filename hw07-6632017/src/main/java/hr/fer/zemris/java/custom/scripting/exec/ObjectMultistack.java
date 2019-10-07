package hr.fer.zemris.java.custom.scripting.exec;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ObjectMultistack {
	
	private Map<String,ValueWrapper> map ;
	private Map<String,MultistackEntry> stacks;

	public ObjectMultistack() {
		map = new HashMap<String, ValueWrapper>();
		stacks = new HashMap<String, MultistackEntry>();
	}
	
	public void push(String keyName, ValueWrapper valueWrapper) {
		MultistackEntry stack = null;
	    ValueWrapper value;
		
		if(!map.containsKey(keyName)) {
			stack = new MultistackEntry(valueWrapper);
		
			value = stack.firstElement();
			map.put(keyName, value);
			stacks.put(keyName, stack);
		} else {
			stack = findStack(keyName);
			value = stack.firstElement();
			stack.addValue(valueWrapper);
		}
	}
	
	private MultistackEntry findStack(String name) {
		MultistackEntry stack = null;
		for (Map.Entry<String, MultistackEntry> entry : stacks.entrySet()) {
			if(entry.getKey().equals(name)) {
				stack = entry.getValue();
				break;
			}
		}
		if(stack == null) {
			throw new IllegalArgumentException("there is no stack with that name");
		}
		return stack;
	}
	
	
	public ValueWrapper pop(String keyName){
		if(!stacks.containsKey(keyName)){
			throw new IllegalArgumentException("there is no stack with that name");
		}
		MultistackEntry stack = findStack(keyName);
		return stack.pop();
	}
	
	public ValueWrapper peek(String keyName){
		
		if(!stacks.containsKey(keyName)){
			throw new IllegalArgumentException("there is no stack with that name");
		}
		
		MultistackEntry stack = findStack(keyName);
		return stack.peek();
	}
	
	public boolean isEmpty(String keyName){
		MultistackEntry stack = findStack(keyName);

		return stack.isEmpty();	
	}
	
	public  static class MultistackEntry{
		
		private List<ValueWrapper> stack;
		private ValueWrapper value;
		
		public MultistackEntry(ValueWrapper value) {
			stack = new LinkedList<ValueWrapper>();
			this.value = value;
			addValue(value);
		}
		private void addValue(ValueWrapper value) {
			stack.add(value);

		}
		
		
		private ValueWrapper peek() {
			if(stack.isEmpty()) {
				throw new IllegalArgumentException("stack is already empty");
			}
		
			ValueWrapper value = stack.get(stack.size() - 1);			
			return value;
		}
		
		private ValueWrapper pop() {
			if(stack.isEmpty()) {
				throw new IllegalArgumentException("stack is already empty");
			}
			//ValueWrapper value = stack.get(stack.size() - 1);	
			return stack.remove(stack.size() - 1);
		}
		
		public ValueWrapper firstElement() {
			if(stack.isEmpty()) {
				throw new IllegalArgumentException("this stack does not contains any value");
			}
			return stack.get(0);
		}
		
		public boolean isEmpty() {
			
			return stack.size() <= 0 ;
		}
		
	}
	
}
