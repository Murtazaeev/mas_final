import React, { useState, useEffect } from "react";
import axios from "axios";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";

interface Column {
  id: "FirstName" | "Lastname" | "Email";
  label: string;
}

const columns: readonly Column[] = [
  { id: "FirstName", label: "FirstName" },
  { id: "Lastname", label: "Lastname" },
  { id: "Email", label: "Email" },
];

interface CustomerData {
  id: string;
  address: string | null;
  email: string;
  name: string;
  surname: string;
}

interface CustomerListTableProps {
  setSelectedCustomerId: React.Dispatch<React.SetStateAction<string | null>>;
  setShowOrders: React.Dispatch<React.SetStateAction<boolean>>;
  setSelectedCustomerName: React.Dispatch<React.SetStateAction<string>>;
  setSelectedCustomerSurname: React.Dispatch<React.SetStateAction<string>>;
}

const CustomerListTable: React.FC<CustomerListTableProps> = ({
  setSelectedCustomerId,
  setShowOrders,
  setSelectedCustomerName,
  setSelectedCustomerSurname,
}) => {
  const [customers, setCustomers] = useState<CustomerData[]>([]);

  useEffect(() => {
    const fetchCustomers = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/customer/getAllCustomers"
        );
        setCustomers(response.data);
      } catch (error) {
        console.error("There was an error fetching the customers!", error);
      }
    };

    fetchCustomers();
  }, []);

  const handleRowClick = (customer: CustomerData) => {
    setSelectedCustomerId(customer.id);
    setSelectedCustomerName(customer.name);
    setSelectedCustomerSurname(customer.surname);
    setShowOrders(true);
  };

  return (
    <div className="flex flex-col h-[550px] min-h-[680px]">
      <div className="flex justify-around items-center text-2xl text-gray-700 font-semibold mb-10 h-20 bg-gray-300 rounded-md">
        <span>Customer list</span>
      </div>

      <Paper
        sx={{
          marginLeft: "auto",
          marginRight: "auto",
          width: "60%",
          overflow: "hidden",
          border: "1px solid grey",
          marginBottom: "auto",
        }}
      >
        <TableContainer sx={{ maxHeight: 400 }}>
          <Table stickyHeader aria-label="sticky table">
            <TableHead>
              <TableRow>
                {columns.map((column) => (
                  <TableCell
                    align="center"
                    key={column.id}
                    sx={{
                      backgroundColor: "black",
                    }}
                  >
                    <span className=" text-white text-lg font-medium">
                      {column.label}
                    </span>
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {customers.map((customer) => (
                <TableRow
                  hover
                  role="checkbox"
                  tabIndex={-1}
                  key={customer.id}
                  onClick={() => handleRowClick(customer)}
                  sx={{
                    cursor: "pointer",
                    "&:hover": {
                      backgroundColor: "rgba(0, 0, 0, 0.04)",
                    },
                  }}
                >
                  <TableCell align="center">{customer.name}</TableCell>
                  <TableCell align="center">{customer.surname}</TableCell>
                  <TableCell align="center">{customer.email}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Paper>
    </div>
  );
};

export default CustomerListTable;
