package handlers

import (
	"bytes"
	"net/http"
	"net/http/httptest"
	"testing"
)

//TestHandleCartGet tests the GET handler for the /carts route.
func TestHandleCartGet(t *testing.T) {

	request, err := http.NewRequest("GET", "/carts?customerID=1", nil)

	if err != nil {

		t.Fatal(err)
	}

	responseRecorder := httptest.NewRecorder()
	handler := http.HandlerFunc(HandleCartGet(client))

	handler.ServeHTTP(responseRecorder, request)

	if status := responseRecorder.Code; status != http.StatusOK {

		t.Errorf("Expected : %v  status code and got : %v status code", http.StatusOK, status)
	}

	expexted := "{\"ID\":1,\"CartElements\":[{\"ElementID\":1,\"ProductID\":1,\"Quantity\":1,\"UnitPrice\":12}],\"TimeStamp\":\"\",\"CustomerID\":1,\"TotalPrice\":0}"

	if responseRecorder.Body.String() != expexted {

		t.Errorf("Expected : %v and got %v", expexted, responseRecorder.Body.String())
	}

	t.Log("TestHandleCartGet has passed with success")

}

//TestHandleCartPost tests the POST handler for the /carts route.
func TestHandleCartPost(t *testing.T) {

	bodyString := "{\"ID\":9,\"CartElements\":[{\"ElementID\":1,\"ProductID\":1,\"Quantity\":1,\"UnitPrice\":1}],\"TimeStamp\":\"\",\"CustomerID\":9,\"TotalPrice\":0}"
	bodyBuffer := bytes.NewBufferString(bodyString)
	request, err := http.NewRequest("POST", "/carts", bodyBuffer)

	if err != nil {

		t.Fatal(err)
	}

	responseRecorder := httptest.NewRecorder()
	handler := http.HandlerFunc(HandleCartPost(client))
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
func TestHandleCartPut(t *testing.T) {

	TestHandleCartPost(t)
}

//TestHandleCartDelete tests the DELETE handler for the /carts route.
func TestHandleCartDelete(t *testing.T) {

	request, err := http.NewRequest("DELETE", "/carts?customerID=9", nil)

	if err != nil {

		t.Fatal(err)
	}

	responseRecorder := httptest.NewRecorder()
	handler := http.HandlerFunc(HandleCartDelete(client))
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
