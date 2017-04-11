package handlers

import (
	"bytes"
	"net/http"
	"net/http/httptest"
	"testing"
)

//TestHandleCartElementGet tests the GET handler for the /cartElement route.
func TestHandleCartElementGet(t *testing.T) {

	request, err := http.NewRequest("GET", "api/v1/cartElement/1/1", nil)

	if err != nil {

		t.Fatal(err)
	}

	responseRecorder := httptest.NewRecorder()
	handler := http.HandlerFunc(HandleCartElementGet(client))

	handler.ServeHTTP(responseRecorder, request)

	if status := responseRecorder.Code; status != http.StatusOK {

		t.Errorf("Expected : %v  status code and got : %v status code", http.StatusOK, status)
	}

	expexted := "{\"ElementID\":1,\"ProductID\":1,\"Quantity\":1,\"UnitPrice\":12}"

	if responseRecorder.Body.String() != expexted {

		t.Errorf("Expected : %v and got %v", expexted, responseRecorder.Body.String())
	}

	t.Log("TestHandleCartGet has passed with success")

}

//TestHandleCartPost tests the POST handler for the /carts route.
func TestHandleCarElementPost(t *testing.T) {

	bodyString := "{\"CustomerID\": 1, [{\"ElementID\":2,\"ProductID\":1,\"Quantity\":1,\"UnitPrice\":1}]}"
	bodyBuffer := bytes.NewBufferString(bodyString)
	request, err := http.NewRequest("POST", "api/v1/cartElement", bodyBuffer)

	if err != nil {

		t.Fatal(err)
	}

	responseRecorder := httptest.NewRecorder()
	handler := http.HandlerFunc(HandleCartElementPost(client))
	handler.ServeHTTP(responseRecorder, request)

	if status := responseRecorder.Code; status != http.StatusOK {

		t.Errorf("Expected : %v  staus code and got : %v staus code", http.StatusOK, status)

	}

	expected := "{\"message\" : \"OK\"}"

	if responseRecorder.Body.String() != expected {

		t.Errorf("Expected : %v and got %v", expected, responseRecorder.Body.String())
	}

	t.Log("TestHandleCartPost has passed with success")
}

//TestHandleCartPut tests the POST handler for the /carts route.
func TestHandleCarElmementPut(t *testing.T) {

	TestHandleCarElementPost(t)
}

//TestHandleCartDelete tests the DELETE handler for the /carts route.
func TestHandleCartElementDelete(t *testing.T) {

	request, err := http.NewRequest("DELETE", "api/v1/cartElement/1/2", nil)

	if err != nil {

		t.Fatal(err)
	}

	responseRecorder := httptest.NewRecorder()
	handler := http.HandlerFunc(HandleCartElementDelete(client))
	handler.ServeHTTP(responseRecorder, request)

	if status := responseRecorder.Code; status != http.StatusOK {

		t.Errorf("Expected : %v  staus code and got : %v staus code", http.StatusOK, status)

	}

	expected := "{\"message\" : \"OK\"}"

	if responseRecorder.Body.String() != expected {

		t.Errorf("Expected : %v and got %v", expected, responseRecorder.Body.String())
	}

	t.Log("TestHandleCartPost has passed with success")
}
