package com.java.design.Interface;

public class InterfaceDesign {
	
	
	
	
	/**
	 * ����� static , ������main ����ʵ����
	 * @author Derek.pan
	 *
	 */
	public static class Button {
		
		public int id;
		public String text;
		public clickListener listener;
	
		
		public Button() {
			super();
		}


		public void setOnClick(clickListener listener ){
			 this.listener = listener;
		}
		
		public void call(){
			if(listener != null){
				listener.change();
			}
		}
		
	}
	
	
	public interface clickListener{
		public void change();
	}
	
	public static void main(String [] args){
		InterfaceDesign.Button b = new InterfaceDesign.Button();
		b.setOnClick(new InterfaceDesign.clickListener() {			
			public void change() {
					System.out.println("execute change");
			}
		});
		
	//	b.call();
		
	}

}
