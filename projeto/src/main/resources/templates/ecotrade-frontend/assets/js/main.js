document.addEventListener("DOMContentLoaded", () => {
  console.log(
    "%cEcoTrade Frontend Inicializado 🌱",
    "color: green; font-weight: bold;"
  );

  // Função auxiliar para aplicar feedback de validação do Bootstrap
  function applyValidationFeedback(
    inputElement,
    feedbackElement,
    isValid,
    message = ""
  ) {
    if (isValid) {
      inputElement.classList.remove("is-invalid");
      inputElement.classList.add("is-valid");
      feedbackElement.textContent = "";
    } else {
      inputElement.classList.remove("is-valid");
      inputElement.classList.add("is-invalid");
      feedbackElement.textContent = message;
    }
  }

  // Função auxiliar para limpar o feedback de validação
  function clearValidationFeedback(form) {
    form.querySelectorAll(".is-invalid, .is-valid").forEach((el) => {
      el.classList.remove("is-invalid", "is-valid");
    });
    form.querySelectorAll(".invalid-feedback").forEach((el) => {
      el.textContent = "";
    });
  }

  // ======= LOGIN =======
  const loginForm = document.getElementById("formLogin");
  if (loginForm) {
    loginForm.addEventListener("submit", (e) => {
      e.preventDefault();

      clearValidationFeedback(loginForm);

      const emailInput = document.getElementById("floatingInput");
      const passwordInput = document.getElementById("floatingPassword");
      const emailFeedback = document.getElementById("emailFeedback");
      const passwordFeedback = document.getElementById("passwordFeedback");

      const email = emailInput.value.trim();
      const password = passwordInput.value.trim();

      let formIsValid = true;

      if (!email) {
        applyValidationFeedback(
          emailInput,
          emailFeedback,
          false,
          "O campo Email é obrigatório."
        );
        formIsValid = false;
      } else {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
          applyValidationFeedback(
            emailInput,
            emailFeedback,
            false,
            "Formato de email inválido."
          );
          formIsValid = false;
        } else {
          applyValidationFeedback(emailInput, emailFeedback, true);
        }
      }

      if (!password) {
        applyValidationFeedback(
          passwordInput,
          passwordFeedback,
          false,
          "O campo Senha é obrigatório."
        );
        formIsValid = false;
      } else {
        applyValidationFeedback(passwordInput, passwordFeedback, true);
      }

      if (formIsValid) {
        let userType = "producer";
        if (email.includes("@empresa.com")) {
          userType = "company";
        } else if (email.includes("@admin.com")) {
          userType = "admin";
        }

        localStorage.setItem("userType", userType);

        showMessage("✅ Login realizado com sucesso!", "success");
        loginForm.reset();
        setTimeout(() => (window.location.href = "dashboard.html"), 1500);
      }
    });
  }

  // ======= CADASTRO =======
  const cadastroForm = document.getElementById("formCadastro");
  if (cadastroForm) {
    cadastroForm.addEventListener("submit", (e) => {
      e.preventDefault();

      clearValidationFeedback(cadastroForm);

      const nameInput = document.getElementById("floatingName");
      const emailInput = document.getElementById("floatingInput");
      const passwordInput = document.getElementById("floatingPassword");
      const confirmPasswordInput = document.getElementById(
        "floatingConfirmPassword"
      );
      const userTypeSelect = document.getElementById("floatingUserType");

      const nameFeedback = document.getElementById("nameFeedback");
      const emailFeedback = document.getElementById("emailFeedback");
      const passwordFeedback = document.getElementById("passwordFeedback");
      const confirmPasswordFeedback = document.getElementById(
        "confirmPasswordFeedback"
      );
      const userTypeFeedback = document.getElementById("userTypeFeedback");

      const name = nameInput.value.trim();
      const email = emailInput.value.trim();
      const password = passwordInput.value.trim();
      const confirmPassword = confirmPasswordInput.value.trim();
      const userType = userTypeSelect.value;

      let formIsValid = true;

      if (!name) {
        applyValidationFeedback(
          nameInput,
          nameFeedback,
          false,
          "O campo Nome é obrigatório."
        );
        formIsValid = false;
      } else {
        applyValidationFeedback(nameInput, nameFeedback, true);
      }

      if (!email) {
        applyValidationFeedback(
          emailInput,
          emailFeedback,
          false,
          "O campo Email é obrigatório."
        );
        formIsValid = false;
      } else {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
          applyValidationFeedback(
            emailInput,
            emailFeedback,
            false,
            "Formato de email inválido."
          );
          formIsValid = false;
        } else {
          applyValidationFeedback(emailInput, emailFeedback, true);
        }
      }

      if (!password) {
        applyValidationFeedback(
          passwordInput,
          passwordFeedback,
          false,
          "O campo Senha é obrigatório."
        );
        formIsValid = false;
      } else {
        const minLength = 8;
        const hasUpperCase = /[A-Z]/.test(password);
        const hasNumber = /\d/.test(password);
        const hasSymbol = /[!@#$%^&*(),.?":{}|<>]/.test(password);

        if (
          password.length < minLength ||
          !hasUpperCase ||
          !hasNumber ||
          !hasSymbol
        ) {
          applyValidationFeedback(
            passwordInput,
            passwordFeedback,
            false,
            "A senha deve ter 8+ caracteres, 1 maiúscula, 1 número e 1 símbolo."
          );
          formIsValid = false;
        } else {
          applyValidationFeedback(passwordInput, passwordFeedback, true);
        }
      }

      if (!confirmPassword) {
        applyValidationFeedback(
          confirmPasswordInput,
          confirmPasswordFeedback,
          false,
          "A confirmação de Senha é obrigatória."
        );
        formIsValid = false;
      } else {
        if (password !== confirmPassword) {
          applyValidationFeedback(
            confirmPasswordInput,
            confirmPasswordFeedback,
            false,
            "As senhas não coincidem."
          );
          formIsValid = false;
        } else {
          applyValidationFeedback(
            confirmPasswordInput,
            confirmPasswordFeedback,
            true
          );
        }
      }

      if (userType === "Selecione o Tipo de Usuário" || !userType) {
        applyValidationFeedback(
          userTypeSelect,
          userTypeFeedback,
          false,
          "Selecione um tipo de usuário."
        );
        formIsValid = false;
      } else {
        applyValidationFeedback(userTypeSelect, userTypeFeedback, true);
      }

      if (formIsValid) {
        localStorage.setItem("userType", userType);

        showMessage("✅ Cadastro realizado com sucesso!", "success");
        cadastroForm.reset();
        setTimeout(() => (window.location.href = "login.html"), 1500);
      }
    });
  }

  // ======= FORM DE CRÉDITOS =======
  const creditForm = document.getElementById("formCredit");
  if (creditForm) {
    creditForm.addEventListener("submit", (e) => {
      e.preventDefault();
      if (!creditForm.checkValidity()) {
        creditForm.reportValidity();
        return;
      }

      showMessage("🌿 Crédito registrado com sucesso!", "success");
      creditForm.reset();
    });
  }

  // ======= FORM DE VENDA DE CRÉDITOS (MARKETPLACE) =======
  const sellCreditsForm = document.getElementById("formSellCredits");
  if (sellCreditsForm) {
    sellCreditsForm.addEventListener("submit", (e) => {
      e.preventDefault();

      // Validação nativa do HTML5 (já que adicionamos 'required' e 'novalidate')
      if (!sellCreditsForm.checkValidity()) {
        sellCreditsForm.classList.add("was-validated");
        return;
      }

      const quantity = document.getElementById("sellQuantity").value;
      const price = document.getElementById("sellPrice").value;
      const description = document.getElementById("sellDescription").value;

      console.log("Dados para venda:", { quantity, price, description });

      showMessage("✅ Oferta de venda publicada com sucesso!", "success");
      sellCreditsForm.reset();
      sellCreditsForm.classList.remove("was-validated");
    });
  }

  // ======= NAVEGAÇÃO ATIVA =======
  const currentPath = window.location.pathname.split("/").pop();
  document.querySelectorAll(".bottom-nav .nav-link").forEach((link) => {
    const linkPath = link.getAttribute("href");
    if (
      linkPath === currentPath ||
      (currentPath === "" && linkPath.includes("index"))
    ) {
      link.classList.add("active");
    } else {
      link.classList.remove("active");
    }
  });

  // ======= BOTÕES DE COMPRA =======
  const buyButtons = document.querySelectorAll("#buy .btn-primary");
  buyButtons.forEach((button) => {
    button.addEventListener("click", (e) => {
      e.preventDefault();
      button.disabled = true;
      const originalText = button.textContent;
      button.textContent = "Processando...";

      setTimeout(() => {
        button.disabled = false;
        button.textContent = originalText;
        showMessage("💰 Compra concluída com sucesso!", "success");
      }, 1500);
    });
  });

  // ======= GRÁFICOS =======
  loadCharts();
});

function loadCharts() {
  const chartPlaceholders = document.querySelectorAll(".chart-placeholder");
  chartPlaceholders.forEach((placeholder, index) => {
    const canvas = document.createElement("canvas");
    placeholder.innerHTML = "";
    placeholder.appendChild(canvas);

    new Chart(canvas, {
      type: index % 2 === 0 ? "doughnut" : "bar",
      data: {
        labels: ["Créditos", "Usados", "Restantes"],
        datasets: [
          {
            data: [65, 25, 10],
            backgroundColor: ["#4caf50", "#00bcd4", "#a5d6a7"],
            borderWidth: 1,
            hoverOffset: 10,
          },
        ],
      },
      options: {
        responsive: true,
        plugins: { legend: { display: false }, tooltip: { enabled: true } },
        animation: { duration: 1500, easing: "easeOutQuart" },
        scales:
          index % 2 === 0
            ? {}
            : {
                y: { beginAtZero: true, ticks: { color: "#2e7d32" } },
                x: { ticks: { color: "#2e7d32" } },
              },
      },
    });
  });
}

// ======= MENSAGEM FLUTUANTE =======
function showMessage(text, type = "info") {
  const message = document.createElement("div");
  message.className = `floating-message bg-${
    type === "success" ? "success" : "info"
  } text-white px-4 py-2 rounded shadow`;
  message.style.position = "fixed";
  message.style.bottom = "20px";
  message.style.right = "20px";
  message.style.zIndex = "9999";
  message.style.opacity = "0";
  message.style.transition = "opacity 0.4s ease, transform 0.4s ease";
  message.style.transform = "translateY(20px)";
  message.textContent = text;

  document.body.appendChild(message);

  setTimeout(() => {
    message.style.opacity = "1";
    message.style.transform = "translateY(0)";
  }, 100);

  setTimeout(() => {
    message.style.opacity = "0";
    message.style.transform = "translateY(20px)";
    setTimeout(() => message.remove(), 400);
  }, 3000);
}

window.aprovarCredito = function (id) {
  showMessage(`✅ Crédito ${id} APROVADO com sucesso!`, "success");
};

window.rejeitarCredito = function (id) {
  showMessage(`❌ Crédito ${id} REJEITADO.`, "info");
};

function getSimulatedUserType() {
  return localStorage.getItem("userType") || "producer";
}

function updateNavigation() {
  const userType = getSimulatedUserType();
  const bottomNav = document.querySelector(".bottom-nav .row");

  if (!document.getElementById("nav-historico")) {
    const historicoLink = `
      <div class="col">
        <a href="historico.html" class="nav-link" id="nav-historico">
          <i class="fas fa-history"></i>
          <span>Histórico</span>
        </a>
      </div>
    `;
    bottomNav.insertAdjacentHTML("beforeend", historicoLink);
  }

  if (userType === "admin" && !document.getElementById("nav-auditoria")) {
    const auditoriaLink = `
      <div class="col">
        <a href="auditoria.html" class="nav-link" id="nav-auditoria">
          <i class="fas fa-clipboard-check"></i>
          <span>Auditoria</span>
        </a>
      </div>
    `;
    bottomNav.insertAdjacentHTML("beforeend", auditoriaLink);
  }

  const registroLink = document.querySelector('a[href="registro.html"]');
  if (registroLink) {
    if (userType !== "producer") {
      registroLink.closest(".col").style.display = "none";
    } else {
      registroLink.closest(".col").style.display = "block";
    }
  }
}

document.addEventListener("DOMContentLoaded", updateNavigation);

// ======= LINK COM JAVA  =======
