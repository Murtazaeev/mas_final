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
import Select, { SelectChangeEvent } from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import { CustomerData } from "./CustomerListTable";
import EditIcon from "@mui/icons-material/Edit";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from "@mui/icons-material/Delete";

interface OrderData {
  id: string;
  orderDate: string;
  orderState: string;
  retailPrice: number;
  edit: string;
  delete: string;
}

interface InvoiceData {
  id: string;
  taxRateInPercentage: number;
  nettoPrice: number;
  bruttoPrice: number;
}

interface OrderListTableProps {}
const columns: readonly { id: keyof OrderData | "edit"; label: string }[] = [
  { id: "id", label: "Order ID" },
  { id: "orderDate", label: "Order Date" },
  { id: "orderState", label: "Order Status" },
  { id: "retailPrice", label: "Retail Price" },
  { id: "edit", label: "Edit" },
  { id: "delete", label: "Delete" },
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
  const [editOrderId, setEditOrderId] = useState<string | null>(null);
  const [newOrderState, setNewOrderState] = useState<string>("");
  const [deleteOrderId, setDeleteOrderId] = useState<string | null>(null);
  const [deleteOpen, setDeleteOpen] = useState(false);

  const { customerId } = useParams<{ customerId: string }>();

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (customerId) {
          const orderResponse = await axios.get<OrderData[]>(
            `http://localhost:8080/order/customerOrders/${customerId}`
          );
          setOrders(orderResponse.data);

          const customerResponse = await axios.get<CustomerData>(
            `http://localhost:8080/customer/userDetails/${customerId}`
          );
          setCustomers(customerResponse.data);
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [customerId]);

  const handleGetInvoice = async (order: OrderData) => {
    if (
      order.orderState === "CANCELED" ||
      order.orderState === "WAITING_FOR_SUPPLY"
    ) {
      setInvoiceData(null);
      setOpen(true);
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8080/invoice/${order.id}`
      );
      setInvoiceData(response.data);
      setOpen(true);
    } catch (error) {
      console.error("Error fetching invoice:", error);
      setInvoiceData(null);
      setOpen(true); // Ensure dialog opens even on error
    }
  };

  const handleRowClick = (order: OrderData) => {
    if (editOrderId !== order.id) {
      handleGetInvoice(order);
    }
  };

  const handleOrderStateChange = async (orderId: string, newState: string) => {
    try {
      await axios.put(
        `http://localhost:8080/order/${orderId}`,
        { orderState: newState },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      // Update the order state in the local state immediately
      setOrders((prevOrders) =>
        prevOrders.map((order) =>
          order.id === orderId ? { ...order, orderState: newState } : order
        )
      );

      // Ensure the selected order state is updated immediately
      setSelectedOrder((prevOrder) =>
        prevOrder && prevOrder.id === orderId
          ? { ...prevOrder, orderState: newState }
          : prevOrder
      );
    } catch (error) {
      console.error("Error updating order state:", error);
    }
  };
  const handleEditClick = (
    event: React.MouseEvent<HTMLButtonElement, MouseEvent>,
    orderId: string,
    currentState: string
  ) => {
    event.stopPropagation();
    setEditOrderId(orderId);
    setNewOrderState(currentState);
  };

  const handleEditChange = (event: SelectChangeEvent<string>) => {
    setNewOrderState(event.target.value as string);
  };

  const handleSaveEdit = async (orderId: string) => {
    await handleOrderStateChange(orderId, newOrderState);
    setEditOrderId(null);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleDeleteOrder = async (orderId: string) => {
    try {
      await axios.delete(`http://localhost:8080/order/${orderId}`);
      setOrders((prevOrders) =>
        prevOrders.filter((order) => order.id !== orderId)
      );
      setDeleteOpen(false);
    } catch (error) {
      console.error("Error deleting order:", error);
    }
  };

  const handleDeleteClick = (orderId: string) => {
    setDeleteOrderId(orderId);
    setDeleteOpen(true);
  };

  const handleDeleteClose = () => {
    setDeleteOpen(false);
    setDeleteOrderId(null);
  };

  const handleConfirmDelete = () => {
    if (deleteOrderId) {
      handleDeleteOrder(deleteOrderId);
    }
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
        <TableContainer sx={{ maxHeight: 500 }}>
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
                    {" "}
                    <TableCell align="center">{order.id}</TableCell>
                    <TableCell align="center">{order.orderDate}</TableCell>
                    <TableCell align="center">
                      {editOrderId === order.id ? (
                        <Select
                          value={newOrderState}
                          onChange={handleEditChange}
                        >
                          <MenuItem value="IN_REALIZATION">
                            IN_REALIZATION
                          </MenuItem>
                          <MenuItem value="WAITING_FOR_SUPPLY">
                            WAITING_FOR_SUPPLY
                          </MenuItem>
                          <MenuItem value="CANCELED">CANCELED</MenuItem>
                          <MenuItem value="COMPLETED">COMPLETED</MenuItem>
                          <MenuItem value="ARCHIVED">ARCHIVED</MenuItem>
                        </Select>
                      ) : (
                        order.orderState
                      )}
                    </TableCell>
                    <TableCell align="center">{order.retailPrice}</TableCell>
                    <TableCell align="center">
                      {editOrderId === order.id ? (
                        <>
                          <Button onClick={() => handleSaveEdit(order.id)}>
                            Save
                          </Button>
                          <Button onClick={() => setEditOrderId(null)}>
                            Cancel
                          </Button>
                        </>
                      ) : (
                        <IconButton
                          style={{ color: "green" }}
                          onClick={(e) => {
                            e.stopPropagation();
                            handleEditClick(e, order.id, order.orderState);
                          }}
                        >
                          <EditIcon />
                        </IconButton>
                      )}
                    </TableCell>
                    <TableCell align="center">
                      <IconButton
                        style={{ color: "red" }}
                        onClick={(e) => {
                          e.stopPropagation();
                          handleDeleteClick(order.id);
                        }}
                      >
                        <DeleteIcon />
                      </IconButton>
                    </TableCell>
                  </TableRow>
                ))
              ) : (
                <TableRow>
                  <TableCell colSpan={5} align="center">
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
      <Dialog open={deleteOpen} onClose={handleDeleteClose}>
        <DialogTitle>Delete Order</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Are you sure you want to delete this order?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDeleteClose}>Cancel</Button>
          <Button onClick={handleConfirmDelete} color="secondary">
            Delete
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default OrderListTable;
