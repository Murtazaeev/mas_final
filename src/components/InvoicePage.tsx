import React from "react";

interface InvoiceData {
  id: string;
  taxRateInPercentage: number;
  nettoPrice: number;
  bruttoPrice: number;
}

interface InvoicePageProps {
  invoiceData: InvoiceData;
}

const InvoicePage: React.FC<InvoicePageProps> = ({ invoiceData }) => {
  return (
    <div
      className="flex  w-[600px] h-[200px] border-2 border-gray-500
     bg-gray-300 rounded-lg p-3 mx-auto mt-16"
    >
      <table>
        <tbody>
          <tr>
            <td>Invoice</td>
            <td>{invoiceData.id}</td>
          </tr>
          <tr>
            <td>Tax Rate</td>
            <td>{invoiceData.taxRateInPercentage} %</td>
          </tr>
          <tr>
            <td>Netto</td>
            <td>{invoiceData.nettoPrice} PLN</td>
          </tr>
          <tr>
            <td>Brutto</td>
            <td>{invoiceData.bruttoPrice} PLN</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default InvoicePage;
