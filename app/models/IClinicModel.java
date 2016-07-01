package models;

import com.fasterxml.jackson.databind.JsonNode;

public interface IClinicModel {
	public JsonNode getView();
	public JsonNode getCreate();
	public JsonNode getDelete();
	public JsonNode getEdit();
}