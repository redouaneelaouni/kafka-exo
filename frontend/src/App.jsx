import { useState } from "react";
import "./App.css";

function App() {

  const [customerName, setCustomerName] = useState("");
  const [product, setProduct] = useState("");
  const [quantity, setQuantity] = useState(1);

  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    setMessage("");
    setError("");

    // Validation côté frontend
    if (!customerName.trim()) {
      setError("Le nom du client est obligatoire.");
      return;
    }

    if (!product.trim()) {
      setError("Le produit est obligatoire.");
      return;
    }

    if (quantity <= 0) {
      setError("La quantité doit être supérieure à 0.");
      return;
    }

    const order = {
      customerName: customerName,
      product: product,
      quantity: Number(quantity)
    };

    try {

      const response = await fetch("http://localhost:8080/api/orders", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(order)
      });

      if (!response.ok) {
        throw new Error("Erreur lors de la création de la commande.");
      }

      setMessage("Commande créée avec succès !");

      // Réinitialiser le formulaire
      setCustomerName("");
      setProduct("");
      setQuantity(1);

    } catch (error) {
      setError(error.message);
    }
  };

  return (
      <div className="container">

        <div className="card">

          <h1>Créer une commande</h1>

          <form onSubmit={handleSubmit}>

            <div className="form-group">
              <label htmlFor="customerName">
                Nom du client
              </label>

              <input
                  id="customerName"
                  type="text"
                  value={customerName}
                  onChange={(event) => setCustomerName(event.target.value)}
                  placeholder="Ex : Ali"
              />
            </div>

            <div className="form-group">
              <label htmlFor="product">
                Produit
              </label>

              <input
                  id="product"
                  type="text"
                  value={product}
                  onChange={(event) => setProduct(event.target.value)}
                  placeholder="Ex : Laptop"
              />
            </div>

            <div className="form-group">
              <label htmlFor="quantity">
                Quantité
              </label>

              <input
                  id="quantity"
                  type="number"
                  min="1"
                  value={quantity}
                  onChange={(event) => setQuantity(event.target.value)}
              />
            </div>

            <button type="submit">
              Créer la commande
            </button>

          </form>

          {message && (
              <div className="success">
                {message}
              </div>
          )}

          {error && (
              <div className="error">
                {error}
              </div>
          )}

        </div>

      </div>
  );
}

export default App;
