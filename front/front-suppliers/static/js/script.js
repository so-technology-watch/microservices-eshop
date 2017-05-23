function deleteProduct(id){
	if(id == null){
		window.alert('action impossible');
		return;
	}
	let basepath = window.location.origin;
	let url = basepath+'/deleteProduct/'+id;
	$.ajax({
		url: url,
		type: 'DELETE'
	})
	.done( () => {
		window.location.reload()
	}).fail( () => {
		window.alert('Une erreur est survenue');
	})
}