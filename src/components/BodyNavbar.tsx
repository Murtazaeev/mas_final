import React, { useState } from "react";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import CustomerListTable from "./CustomerListTable";
import OrderListTable from "./OrderListTable";
import InvoicePage from "./InvoicePage";

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}
interface InvoiceData {
  id: string;
  taxRateInPercentage: number;
  nettoPrice: number;
  bruttoPrice: number;
}
function CustomTabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

function a11yProps(index: number) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

function BodyNavbar() {
  const [value, setValue] = useState(0);
  const [selectedCustomerId, setSelectedCustomerId] = useState<string | null>(
    null
  );
  const [showOrders, setShowOrders] = useState(false);
  const [showInvoice, setShowInvoice] = useState(false);
  const [invoiceData, setInvoiceData] = useState<InvoiceData | null>(null);
  const [selectedCustomerName, setSelectedCustomerName] = useState<string>("");
  const [selectedCustomerSurname, setSelectedCustomerSurname] =
    useState<string>("");

  const handleChange = (_event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
    setShowOrders(false);
    setShowInvoice(false);
  };

  return (
    <Box
      sx={{
        width: "100%",
        height: "80px",
        paddingTop: "15px",
        backgroundColor: "#CADFF6",
      }}
    >
      <Box>
        <Tabs
          className="h-[50px]"
          value={value}
          onChange={handleChange}
          aria-label="basic tabs example"
        >
          <Tab
            style={{
              marginLeft: "30px",
              marginRight: "30px",
              width: "200px",
              height: "30px",
              borderRadius: "10px",
              backgroundColor: "white",
              fontWeight: "700",
            }}
            label="Customers"
            {...a11yProps(0)}
          />
          <Tab
            style={{
              marginLeft: "30px",
              marginRight: "30px",
              width: "200px",
              height: "30px",
              borderRadius: "10px",
              backgroundColor: "white",
              fontWeight: "700",
            }}
            label="Events"
            {...a11yProps(1)}
          />
          <Tab
            style={{
              marginLeft: "30px",
              marginRight: "30px",
              width: "200px",
              height: "30px",
              borderRadius: "10px",
              backgroundColor: "white",
              fontWeight: "700",
            }}
            label="My Account"
            {...a11yProps(2)}
          />
        </Tabs>
      </Box>
      <CustomTabPanel value={value} index={0}>
        {!showOrders ? (
          <CustomerListTable
            setSelectedCustomerId={setSelectedCustomerId}
            setShowOrders={setShowOrders}
            setSelectedCustomerName={setSelectedCustomerName}
            setSelectedCustomerSurname={setSelectedCustomerSurname}
          />
        ) : (
          <OrderListTable
            customerId={selectedCustomerId}
            setInvoiceData={setInvoiceData}
            setShowInvoice={setShowInvoice}
            showCustomerList={() => setShowOrders(false)}
            customerName={selectedCustomerName}
            customerSurname={selectedCustomerSurname}
          />
        )}
        {showInvoice && invoiceData && (
          <InvoicePage invoiceData={invoiceData} />
        )}
      </CustomTabPanel>
      <CustomTabPanel value={value} index={1}>
        Events
      </CustomTabPanel>
      <CustomTabPanel value={value} index={2}>
        My Account
      </CustomTabPanel>
    </Box>
  );
}

export default BodyNavbar;
