import Navbar from "./components/Navbar";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Events from "./pages/Events/Events";
import MyAccount from "./pages/My Account/MyAccount";
import OrderListTable from "./components/OrderListTable";
import Cutomer from "./pages/Customers/CustomerList/Cutomer";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/customers" element={<Cutomer />} />
        <Route path="/events" element={<Events />} />
        <Route path="/my-account" element={<MyAccount />} />
        <Route
          path="/customer/:customerId/orders"
          element={<OrderListTable />}
        />
      </Routes>
    </Router>
  );
}

export default App;
