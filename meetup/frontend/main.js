VMasker(document.querySelector("#cel-input")).maskPattern("(99) 99999-9999");

function sendRequest() {
  const celInput = document.querySelector("#cel-input");
  const celValue = celInput.value.replace(/[^0-9]/g, "");
  const startSection = document.querySelector("#start-section");
  const endSection = document.querySelector("#end-section");
  const sendBtn = document.querySelector("#send-btn");

  console.log("phone", celValue);

  if (celValue.length !== 11) {
    celInput.classList.add("is-danger");
    animateCSS("#input-div", "bounce");
  } else {
    celInput.disabled = true;
    sendBtn.classList.toggle("is-loading");
    celInput.classList.remove("is-danger");

    axios
      .post(
        "https://nwmola2xce.execute-api.us-east-1.amazonaws.com/deploy/cellphone",
        { cellphone: celValue }
      )
      .then(resp => {
        console.log("resp", resp);
        celInput.disabled = false;
        celInput.value = "";
        sendBtn.classList.toggle("is-loading");
        startSection.classList.toggle("is-hidden");
        endSection.classList.toggle("is-hidden");
      })
      .catch(err => {
        console.log("err", err);
        celInput.disabled = false;
        sendBtn.classList.toggle("is-loading");
      });
  }
}

function backToStart() {
  const startSection = document.querySelector("#start-section");
  const endSection = document.querySelector("#end-section");

  startSection.classList.toggle("is-hidden");
  endSection.classList.toggle("is-hidden");
}

function animateCSS(element, animationName, callback) {
  const node = document.querySelector(element);
  node.classList.add("animated", animationName);

  function handleAnimationEnd() {
    node.classList.remove("animated", animationName);
    node.removeEventListener("animationend", handleAnimationEnd);
    if (typeof callback === "function") callback();
  }

  node.addEventListener("animationend", handleAnimationEnd);
}
