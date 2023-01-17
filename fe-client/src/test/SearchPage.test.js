import { render, screen } from '@testing-library/react';
import SearchPage from '../components/SearchPage';

test('renders component', () => {
  render(<SearchPage />);
  const linkElement = screen.getByText(/City List/i);
  expect(linkElement).toBeInTheDocument();
});