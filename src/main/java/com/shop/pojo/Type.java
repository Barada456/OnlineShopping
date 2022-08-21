package com.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type
{
	private int typeId;
	private String typeTitle,typeDescription;

	public Type(String typeTitle, String typeDescription) {
		this.typeTitle = typeTitle;
        this.typeDescription = typeDescription;
	}
}
