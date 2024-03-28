import React, { useState, useEffect } from "react";
import axios from "axios";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import Slide from "@mui/material/Slide";
import { TransitionProps } from "@mui/material/transitions";
import { useParams } from "react-router-dom";
import { CustomerData } from "./CustomerListTable";

interface OrderData {
  id: string;
  orderDate: string;
  orderState: string;
  retailPrice: number;
}

interface InvoiceData {
  id: string;
  taxRateInPercentage: number;
  nettoPrice: number;
  bruttoPrice: number;
}

interface OrderListTableProps {}

const columns: readonly { id: keyof OrderData; label: string }[] = [
  { id: "orderDate", label: "Order Date" },
  { id: "orderState", label: "Order Status" },
  { id: "retailPrice", label: "Retail Price" },
];

const Transition = React.forwardRef(function Transition(
  props: TransitionProps & { children: React.ReactElement },
  ref: React.Ref<unknown>
) {
  return <Slide direction="up" ref={ref} {...props} />;
});

const OrderListTable: React.FC<OrderListTableProps> = ({}) => {
  const [orders, setOrders] = useState<OrderData[]>([]);
  const [selectedOrder, setSelectedOrder] = useState<OrderData | null>(null);
  const [open, setOpen] = useState(false);
  const [invoiceData, setInvoiceData] = useState<InvoiceData | null>(null);
  const [customers, setCustomers] = useState<CustomerData>();

  const { customerId } = useParams<{ customerId: string }>();

  useEffect(() => {
    if (customerId) {
      const fetchOrders = async () => {
        try {
          const response = await axios.get(
            `http://localhost:8080/order/customerOrders/${customerId}`
          );
          setOrders(response.data);
        } catch (error) {
          console.error("Error fetching orders:", error);
        }
      };
      fetchOrders();
    }
  }, [customerId]);

  useEffect(() => {
    const fetchCustomers = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/customer/userDetails/${customerId}`
        );
        setCustomers(response.data);
      } catch (error) {
        console.error("There was an error fetching the customers!", error);
      }
    };

    fetchCustomers();
  }, [customerId]);

  const handleGetInvoice = async (order: OrderData) => {
    setSelectedOrder(order);

    if (
      order.orderState === "CANCELED" ||
      order.orderState === "WAITING_FOR_SUPPLY"
    ) {
      setInvoiceData(null);
      setOpen(true);
    } else {
      try {
        const response = await axios.get(
          `http://localhost:8080/invoice/${order.id}`
        );
        setInvoiceData(response.data);
        setOpen(true);
      } catch (error) {
        console.error("Error fetching invoice:", error);
        setInvoiceData(null);
      }
    }
  };

  const handleRowClick = (order: OrderData) => {
    handleGetInvoice(order);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div className="flex flex-col h-[550px] min-h-[680px]">
      <div className="flex justify-around items-center text-2xl text-gray-700 font-semibold mb-20  h-20 bg-gray-300 rounded-md">
        <span>
          Order list of customer: {customers?.name} {customers?.surname}
        </span>
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
                    sx={{ backgroundColor: "black" }}
                    key={column.id}
                  >
                    <span className="text-white text-lg font-medium">
                      {column.label}
                    </span>
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {orders.length > 0 ? (
                orders.map((order) => (
                  <TableRow
                    key={order.id}
                    onClick={() => handleRowClick(order)}
                    hover
                    sx={{
                      cursor: "pointer",
                      "&:hover": {
                        backgroundColor: "rgba(0, 0, 0, 0.04)",
                      },
                    }}
                  >
                    <TableCell align="center">{order.orderDate}</TableCell>
                    <TableCell align="center">{order.orderState}</TableCell>
                    <TableCell align="center">{order.retailPrice}</TableCell>
                  </TableRow>
                ))
              ) : (
                <TableRow>
                  <TableCell colSpan={3} align="center">
                    The selected customer does not have any order at the moment
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
      </Paper>
      <Dialog
        open={open}
        TransitionComponent={Transition}
        onClose={handleClose}
      >
        <DialogTitle>Invoice Details</DialogTitle>
        <DialogContent>
          {invoiceData ? (
            <>
              <DialogContentText>
                <span className="text-black font-semibold">ID:</span>{" "}
                <span className="text-gray-700 font-medium italic">
                  {invoiceData.id}
                </span>
              </DialogContentText>
              <DialogContentText>
                <span className="text-black font-semibold">Status:</span>{" "}
                <span className="text-gray-700 font-medium italic">
                  {selectedOrder?.orderState}
                </span>
              </DialogContentText>
              <DialogContentText>
                <span className="text-black font-semibold">Tax Rate:</span>{" "}
                <span className="text-gray-700 font-medium italic">
                  {invoiceData.taxRateInPercentage}%
                </span>
              </DialogContentText>
              <DialogContentText>
                <span className="text-black font-semibold">Netto Price:</span>{" "}
                <span className="text-gray-700 font-medium italic">
                  {invoiceData.nettoPrice} PLN
                </span>
              </DialogContentText>
              <DialogContentText>
                <span className="text-black font-semibold">Brutto Price:</span>{" "}
                <span className="text-gray-700 font-medium italic">
                  {invoiceData.bruttoPrice} PLN
                </span>
              </DialogContentText>
            </>
          ) : (
            <DialogContentText>
              This invoice cannot be displayed because the order status is
              either cancelled or waiting for supply.
            </DialogContentText>
          )}
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Close</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default OrderListTable;
