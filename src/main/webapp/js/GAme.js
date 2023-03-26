
let dice = document.querySelectorAll(".dice-image");

function roll() {
	dice.forEach(function(die) {
		die.classList.add("shake");
	});
	setTimeout(3000);
}
